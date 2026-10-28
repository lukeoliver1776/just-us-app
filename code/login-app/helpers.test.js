const helpers = require('./helpers');

test('Login accepts the correct username and password', function () {
    var user = {
        username: 'luke',
        password: 'password123'
    };

    var result = helpers.checkLogin(user, 'luke', 'password123');

    expect(result).toBe(true);
});

test('Login rejects the wrong password', function () {
    var user = {
        username: 'luke',
        password: 'password123'
    };

    var result = helpers.checkLogin(user, 'luke', 'wrong');

    expect(result).toBe(false);
});

test('Login rejects the wrong username', function () {
    var user = {
        username: 'luke',
        password: 'password123'
    };

    var result = helpers.checkLogin(user, 'other', 'password123');

    expect(result).toBe(false);
});

test('Login rejects an account that does not exist', function () {
    var result = helpers.checkLogin(undefined, 'luke', 'password123');

    expect(result).toBe(false);
});

test('Account creation accepts a new username and password', function () {
    var result = helpers.getAccountError('luke', 'password123', undefined);

    expect(result).toBe('');
});

test('Account creation rejects an empty username', function () {
    var result = helpers.getAccountError('', 'password123', undefined);

    expect(result).toBe('Please enter a name and password');
});

test('Account creation rejects an empty password', function () {
    var result = helpers.getAccountError('luke', '', undefined);

    expect(result).toBe('Please enter a name and password');
});

test('Account creation rejects missing fields', function () {
    var result = helpers.getAccountError(undefined, undefined, undefined);

    expect(result).toBe('Please enter a name and password');
});

test('Account creation rejects a username that is already taken', function () {
    var user = {
        username: 'luke',
        password: 'password123'
    };

    var result = helpers.getAccountError('luke', 'password123', user);

    expect(result).toBe('That username is already taken');
});