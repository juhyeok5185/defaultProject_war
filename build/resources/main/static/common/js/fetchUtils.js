/**
 * fetch getMethod (csrf 포함)
 * async , await 필요
 * @example await fetchGet(url , params) , await fetchGet(url , formData) , await fetchGet(url)
 * @param url api 주소(String)
 * @param params Json(객체) , FormData
 * @async
 * @example @PathVariable , @RequestParam(생략가능)
 *
 */
async function fetchGet(url, params) {
    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    if (params) {
        url += '?' + getQueryString(params);
    }

    try {
        const response = await fetch(url, {
            headers: {
                [csrfHeader]: csrfToken,
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            alert(await response.text());
            return null;
        }

        return await response.json();

    } catch (error) {
        throw error;
    }
}


/**
 * fetch postMethod (csrf 포함)
 * @param url api 주소(String)
 * @param params Json(객체) , FormData
 * @async
 * @example @RequestBody
 */
async function fetchPost(url, params) {
    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    if (params instanceof FormData) {
        params = changeFormDataToJson(params);
    }

    try {
        const response = await fetch(url, {
            method: "POST",
            headers: {
                [csrfHeader]: csrfToken,
                "Content-Type": "application/json",
            },
            body: JSON.stringify(params),
        });
        if (!response.ok) {
            alert(await response.text());
            throw new Error("API 오류");
        }

        return await response.json();
    } catch (error) {
        throw error;
    }
}

/**
 * fetch postMethod With Files (csrf 포함)
 * @param url api 주소(String)
 * @param formData FormData
 * @async
 * @example 복수 : List<MultipartFile> , 단수 : @RequestPart("file")(생략가능)
 */
async function fetchPostFiles(url, formData) {
    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    try {
        const response = await fetch(url, {
            method: "POST",
            headers: {
                [csrfHeader]: csrfToken,
            },
            body: formData,
        });

        if (!response.ok) {
            alert(await response.text());
            throw new Error("API 오류");
        }

        return await response.json();
    } catch (error) {
        throw error;
    }
}


/**
 * fetch patchMethod (csrf 포함)
 * @param url api 주소(String)
 * @param params Json(객체) , FormData
 * @async
 * @example @RequestBody
 */
async function fetchPatch(url, params) {
    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    if (params instanceof FormData) {
        params = changeFormDataToJson(params);
    }

    try {
        const response = await fetch(url, {
            method: "PATCH",
            headers: {
                [csrfHeader]: csrfToken,
                "Content-Type": "application/json",
            },
            body: JSON.stringify(params),
        });

        if (!response.ok) {
            alert(await response.text());
            throw new Error("API 오류");
        }

        return await response.json();
    } catch (error) {
        throw error;
    }
}


/**
 * fetch patchMethod With Files (csrf 포함)
 * @param url api 주소(String)
 * @param formData FormData
 * @async
 * @example 복수 : List<MultipartFile> , 단수 : @RequestPart("file")(생략가능)
 */
async function fetchPatchFiles(url, formData) {
    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    try {
        const response = await fetch(url, {
            method: "PATCH",
            headers: {
                [csrfHeader]: csrfToken,
            },
            body: formData,
        });

        if (!response.ok) {
            alert(await response.text());
            throw new Error("API 오류");
        }

        return await response.json();

    } catch (error) {
        throw error
    }
}


/**
 * fetch delete (csrf 포함) 검증안됨
 * @param url api 주소(String)
 * @async
 */
async function fetchDelete(url) {
    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    try {
        const response = await fetch(url, {
            method: "DELETE",
            headers: {
                [csrfHeader]: csrfToken,
            },
        });

        if (!response.ok) {
            alert(await response.text());
            throw new Error("API 오류");
        }

        return await response.json();

    } catch (error) {
        throw error;
    }
}


/**
 * Json , FormData를 QueryString 문자열로 변경해준다.
 * @example ?id=1&type=2
 * @param params json(객체) , FormData
 */
function getQueryString(params) {
    if (params instanceof FormData) {
        return Array.from(params.entries())
            .map(entry => `${encodeURIComponent(entry[0])}=${encodeURIComponent(entry[1])}`)
            .join('&');
    } else {
        return Object.keys(params)
            .map(key => encodeURIComponent(key) + '=' + encodeURIComponent(params[key]))
            .join('&');
    }
}


/**
 * url에 queryString 추가해서 변경해준다.
 * @example localhost/admin?id=1&type=2
 * @param params json(객체) , FormData
 */
function changeUrl(params) {
    let queryString = '';
    queryString = '?' + getQueryString(params);
    const uriToChange = location.pathname + queryString;
    history.replaceState(null, '', uriToChange);
}


/**
 * FormData 를 key : value 형태로 print
 * @param params FormData
 */
function logFormData(formData) {
    formData.forEach((value, key) => {
        console.log(`${key} : ${value}`)
    })
}


/**
 * 엘리먼트 조회
 * @example find('input[name=type]');
 * @param selector Selectors
 * @returns Element
 */
function find(selector) {
    return document.querySelector(selector);
}

/**
 * 복수 엘리먼트 조회
 * @example find('input[name=type]')
 * @param selector Selectors
 * @returns Element
 */
function findAll(selector) {
    return document.querySelectorAll(selector);
}


/**
 * name=page 의 값을 변경한다.
 * @param page pageno
 */
function changePageno(page) {
    if (page !== undefined) {
        document.querySelector('input[name=page]').value = page;
    }
}


/**
 * FormData 를 Json으로 리턴해준다.
 * @param params FormData
 */
function changeFormDataToJson(formData) {
    const json = {};
    for (const [key, value] of formData.entries()) {
        json[key] = value;
    }
    return json;
}


/**
 * impty => 빈문자열로 변환
 * @param data String
 */
function isEmpty(data) {
    if (data === null || data === "" || data === "null" || data === undefined || data === "undefined") {
        return "";
    }
    return data
}


/**
 * 주어진 정규식 패턴에 따라 입력된 값이 유효한지 확인하는 함수
 * @param {String} value 확인할 값
 * @param {RegExp} pattern 정규식 패턴
 * @param {String} fieldName 필드 이름 (예: "아이디", "비밀번호")
 * @returns {String} 유효하지 않은 경우 오류 메시지, 유효한 경우 null
 */
function validRegex(value, pattern, message) {
    if (!pattern.test(value)) {
        alert(message);
        throw new Error(message);
    }
    return null;
}


/**
 * url query string 의 value를 가져와주는 메소드
 * @param {name} value가 필요한 queryString 이름
 */
function getParamValue(name) {
    return new URLSearchParams(window.location.search).get(name);
}


/**
 * security 로그인된 아이디를 찾아옴
 * html 에 이게 있어야함 <meta name="username" th:content="${#authentication.principal.username}">
 */
function getLoginId(){
    return document.querySelector('meta[name="username"]').getAttribute('content');
}
