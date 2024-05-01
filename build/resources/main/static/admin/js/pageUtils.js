/**
 * 페이지네이션 HTML draw
 * @param pageData 페이지네이션 객체
 * @param functionName 페이지 이동 시 실행할 함수명
 */
function drawPage(pageData, functionName) {

    // 현재 페이지 번호가 전체 페이지 수보다 크면, 현재 페이지 번호에 전체 페이지 수 저장
    if (pageData.page > pageData.totalPages) {
        pageData.page = pageData.totalPages;
    }

    // 페이지 리스트의 첫 페이지 번호
    let startPage = parseInt((pageData.page - 1) / pageData.pageSize) * pageData.pageSize + 1;

    // 페이지 리스트의 마지막 페이지 번호
    let endPage = parseInt(startPage + pageData.pageSize) - 1;

    // 마지막 페이지 번호가 전체 페이지 수보다 크면, 마지막 페이지 번호에 전체 페이지 수 저장
    if (endPage > pageData.totalPages) {
        endPage = pageData.totalPages;
    }

    const html = makePrevPageLinks(startPage, functionName) +
        				makePageNumbers(startPage, endPage, functionName) +
        				makeNextPageLinks(endPage, pageData.size, pageData.totalElements, pageData.totalPages, functionName);

    const drawTarget = getNode('div.paging');
    drawTarget.innerHTML = html;

    const currentPage = Array.from(drawTarget.getAllNode('button[data-page]')).find(e => (Number(e.textContent) === pageData.page || Number(e.textContent) === pageData.totalPageCount));
    currentPage.classList.add('on');
    currentPage.removeAttribute('onclick');
}

/**
 * 첫/이전 페이지 링크 생성
 * @param startPage 페이지 번호상의 시작 페이지 번호
 * @param functionName 페이지 이동 시 실행할 함수명
 * @returns 이전 페이지 링크 HTML
 */
function makePrevPageLinks(startPage, functionName) {
    return (startPage > 1)
        ? `<button type="button" class="page_bt first" onclick="${functionName}(1);"><span>첫 페이지</span></button>
           <button type="button" class="page_bt prev" onclick="${functionName}(${startPage - 1});"><span>이전 페이지</span></button>`
        : '';
}

/**
 * 페이지 번호 생성
 * @param startPage 페이지 번호상의 시작 페이지 번호
 * @param endPage 페이지 번호상의 끝 페이지 번호
 * @param functionName 페이지 이동 시 실행할 함수명
 */
function makePageNumbers(startPage, endPage, functionName) {
    let html = '<span class="count_num">';
    for (let i = startPage; i <= endPage; i++) {
        html += `<button type="button" data-page="${i}" onclick="${functionName}(${i});">${i}</button>`;
    }
    return html + '</span>';
}

/**
 * 다음/마지막 페이지 링크 생성
 * @param endPage 페이지 번호상의 끝 페이지 번호
 * @param recordSize 페이지당 출력할 레코드 사이즈
 * @param totalRecordCount 전체 레코드 개수
 * @param totalPageCount 전체 페이지 개수
 * @param functionName 페이지 이동 시 실행할 함수명
 * @returns 다음 페이지 링크 HTML
 */
function makeNextPageLinks(endPage, recordSize, totalRecordCount, totalPageCount, functionName) {
	const existNextPage = ((endPage * recordSize) < totalRecordCount);
    return (existNextPage)
        ? `<button type="button" class="page_bt next" onclick="${functionName}(${endPage + 1});"><span>다음 페이지</span></button>
   		   <button type="button" class="page_bt last" onclick="${functionName}(${totalPageCount});"><span>마지막 페이지</span></button>`
        : '';
}

// 실제 페이지 번호 조회
function getRealPageNo(page) {
    const pageParam = new URLSearchParams(location.search).get('page');
    return Number(page ? page : (pageParam ? pageParam : 1));
}

// URL 쿼리 스트링 변경
function changeQueryString(queryString) {
    const uriToChange = location.pathname + '?' + queryString;
    history.replaceState(null, '', uriToChange);
}

/**
 * 리스트 검색 폼 반환
 * @param formId form element ID
 * @param page 페이지 번호
 * @returns search form element
 */
function getListSearchForm(formId, page) {
   const searchForm = getNodeById(formId);
   searchForm.page.value = getRealPageNo(page);
   return searchForm;
}

/**
 * 페이지 데이터 생성
 * @param totalRecordCount 전체 레코드 개수
 * @param totalPageCount 전체 페이지 개수
 */
function makePageData(totalRecordCount, totalPageCount) {
    const searchParams = new URLSearchParams(location.search);
    return {
		  page: Number(searchParams.get('page'))
		, size: Number(searchParams.get('size'))
		, pageSize: Number(searchParams.get('pageSize'))
		, totalElements: totalRecordCount
		, totalPages: totalPageCount
	}
}

/**
 * 페이지네이션 HTML 제거
 */
function removePage() {
    getNode('.paging').innerHTML = '';
}

/**
 * 쿼리 스트링 파라미터를 객체화하여 검색 폼에 세팅 (key = value)
 * @param formId form element ID
 */
function setQueryStringToSearchForm(formId) {
	if ( !location.search ) {
		return false;
	}
    const searchForm = getNodeById(formId);
	new URLSearchParams(location.search).forEach((value, key) => {
		if (searchForm[key]) {
			searchForm[key].value = value;
		}
	});
}

/**
 * 결과 없음 HTML draw
 * @param tbodyId - 대상 tbody ID
 */
function drawNoResultHtml(tbodyId) {
	const tbody = getNodeById(tbodyId);
	if ( !tbody ) {
		return false;
	}
	const colspan = tbody.prevNode().getAllNode('th').length;
    tbody.innerHTML = `<tr><td colspan="${colspan}"><div class="no_data_msg">검색된 결과가 없습니다.</div></td></tr>`;
}

/**
 * 페이지 번호 반환 (데이터 삭제 처리 후 서버로 전달할 페이지 번호)
 * @param tbodyId - 대상 tbody ID
 */
function getPageNoAfterDeletion(tbodyId) {
    const rows = getNode('#' + tbodyId).children;
    const currentPage = Number(new URLSearchParams(location.search).get('page'));
    return (rows.length > 1) ? currentPage : currentPage - 1;
}

/**
 * 데이터가 없는 경우, '결과 없음' 메시지 렌더링 & 페이지네이션 제거
 * @param list - List 데이터
 * @param tbodyId - 대상 tbody ID
 */
function handleEmptyList(list, tbodyId) {
    if (list.length) {
        return true;
    }
    drawNoResultHtml(tbodyId);
    removePage();
    throw new Error();
}

function makePageDataNotUseUrl(data) {
    return {
        page: Number(data.pageable.pageNumber) + 1,
        size: Number(data.size),
        pageSize: Number(data.pageable.pageSize),
        totalElements: Number(data.totalElements),
        totalPages: data.totalPages
    };
}
