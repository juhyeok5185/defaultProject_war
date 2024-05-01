// 전화번호 하이픈(-) 처리
function hyphen(value) {
    if (!isNumber(value)) {
        return '';
    }
    return value.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/g, '$1-$2-$3');
}

// 이름 마스킹 처리
function maskingName(value) {
    if (value.length < 3) {
        return value.replace(/.$/, '*');
    }

    let nameArray = value.split('');
    for (let i = 0, len = nameArray.length; i < len; i++) {
        nameArray[i] = (i < 1 || i === (len - 1)) ? nameArray[i] : '*';
    }
    return nameArray.join().replace(/,/g, '');
}