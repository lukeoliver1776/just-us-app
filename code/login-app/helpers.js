function checkLogin(user, username, password) {
    if (!user) {
        return false;
    }

    return user.username == username && user.password == password;
}

function getAccountError(username, password, user) {
    if (!username || !password) {
        return 'Please enter a name and password';
    }

    if (user) {
        return 'That username is already taken';
    }

    return '';
}

module.exports = {
    checkLogin: checkLogin,
    getAccountError: getAccountError
};