function hasKey(obj, key) {
    return obj.hasOwnProperty(key);
}

function hasValue(obj, value) {
    return Object.values(obj).includes(value);
}

module.exports = {
    hasKey,
    hasValue
}
