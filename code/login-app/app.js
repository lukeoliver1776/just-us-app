const express = require('express');
const session = require('express-session');
const sqlite3 = require('sqlite3').verbose();
const helpers = require('./helpers');

const app = express();
const db = new sqlite3.Database('users.db');

app.use(express.urlencoded({ extended: true }));

app.use(session({
    secret: 'secret'
}));

db.run('CREATE TABLE IF NOT EXISTS users (username TEXT, password TEXT)');

app.get('/', function (req, res) {
    res.redirect('/login');
});

app.get('/login', function (req, res) {
    res.send(
        '<h1>Login</h1>' +
        '<form method="POST" action="/login">' +
        'Username: <input type="text" name="username"><br><br>' +
        'Password: <input type="password" name="password"><br><br>' +
        '<button type="submit">Login</button>' +
        '</form>' +
        '<br><a href="/create">Create an account</a>'
    );
});

app.post('/login', function (req, res) {
    var username = req.body.username;
    var password = req.body.password;

    var sql = 'SELECT * FROM users WHERE username = ?';

    db.get(sql, [username], function (err, row) {
        if (err) {
            res.status(500).send('Database error');
            return;
        }

        if (helpers.checkLogin(row, username, password)) {
            req.session.username = username;
            res.redirect('/home');
        } else {
            res.send(
                '<h1>Login</h1>' +
                '<p>Wrong username or password</p>' +
                '<form method="POST" action="/login">' +
                'Username: <input type="text" name="username"><br><br>' +
                'Password: <input type="password" name="password"><br><br>' +
                '<button type="submit">Login</button>' +
                '</form>' +
                '<br><a href="/create">Create an account</a>'
            );
        }
    });
});

app.get('/create', function (req, res) {
    res.send(
        '<h1>Create Account</h1>' +
        '<form method="POST" action="/create">' +
        'Name: <input type="text" name="username"><br><br>' +
        'Password: <input type="password" name="password"><br><br>' +
        '<button type="submit">Create Account</button>' +
        '</form>' +
        '<br><a href="/login">Go to login</a>'
    );
});

app.post('/create', function (req, res) {
    var username = req.body.username;
    var password = req.body.password;

    var sql = 'SELECT * FROM users WHERE username = ?';

    db.get(sql, [username], function (err, row) {
        if (err) {
            res.status(500).send('Database error');
            return;
        }

        var error = helpers.getAccountError(username, password, row);

        if (error != '') {
            res.send(
                '<h1>Create Account</h1>' +
                '<p>' + error + '</p>' +
                '<form method="POST" action="/create">' +
                'Name: <input type="text" name="username"><br><br>' +
                'Password: <input type="password" name="password"><br><br>' +
                '<button type="submit">Create Account</button>' +
                '</form>' +
                '<br><a href="/login">Go to login</a>'
            );
        } else {
            var sql = 'INSERT INTO users (username, password) VALUES (?, ?)';

            db.run(sql, [username, password], function (err) {
                if (err) {
                    res.status(500).send('Database error');
                    return;
                }

                req.session.username = username;
                res.redirect('/home');
            });
        }
    });
});

app.get('/home', function (req, res) {
    if (req.session.username) {
        res.send('<h1>Welcome, ' + req.session.username + '</h1>');
    } else {
        res.redirect('/login');
    }
});

app.listen(3000, function () {
    console.log('Server is running on port 3000');
});
