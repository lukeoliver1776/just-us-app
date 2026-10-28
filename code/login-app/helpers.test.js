const test = require('node:test');
const assert = require('node:assert/strict');
const helpers = require('./helpers');

test('Login accepts the correct username and password', function () {
    var user = {
        username: 'luke',
        password: 'password123'
    };

    var result = helpers.checkLogin(user, 'luke', 'password123');

    assert.equal(result, true);
});

test('Login rejects the wrong password', function () {
    var user = {
        username: 'luke',
        password: 'password123'
    };

    var result = helpers.checkLogin(user, 'luke', 'wrong');

    assert.equal(result, false);
});

test('Login rejects the wrong username', function () {
    var user = {
        username: 'luke',
        password: 'password123'
    };

    var result = helpers.checkLogin(user, 'other', 'password123');

    assert.equal(result, false);
});

test('Login rejects an account that does not exist', function () {
    var result = helpers.checkLogin(undefined, 'luke', 'password123');

    assert.equal(result, false);
});

test('Account creation accepts a new username and password', function () {
    var result = helpers.getAccountError('luke', 'password123', undefined);

    assert.equal(result, '');
});

test('Account creation rejects an empty username', function () {
    var result = helpers.getAccountError('', 'password123', undefined);

    assert.equal(result, 'Please enter a name and password');
});

test('Account creation rejects an empty password', function () {
    var result = helpers.getAccountError('luke', '', undefined);

    assert.equal(result, 'Please enter a name and password');
});

test('Account creation rejects missing fields', function () {
    var result = helpers.getAccountError(undefined, undefined, undefined);

    assert.equal(result, 'Please enter a name and password');
});

test('Account creation rejects a username that is already taken', function () {
    var user = {
        username: 'luke',
        password: 'password123'
    };

    var result = helpers.getAccountError('luke', 'password123', user);

    assert.equal(result, 'That username is already taken');
});