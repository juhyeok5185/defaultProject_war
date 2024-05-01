/**
 * Pagination <div class="paing"이 있어야 한다.>
 * @param pageData makePageData의 데이터
 * @param functionName 리스트 조회 함수 이름
 */
function drawWebPage(response, functionName) {
    const pageData = makePageData(response.totalElements, response.totalPages);
    if (pageData.page > pageData.totalPages) {
        pageData.page = pageData.totalPages;
    }
    let startPage = parseInt((pageData.page - 1) / pageData.pageSize) * pageData.pageSize + 1;

    let endPage = parseInt(startPage + pageData.pageSize) - 1;

    if (endPage > pageData.totalPages) {
        endPage = pageData.totalPages;
    }

    let html = ``;
    if(startPage>1){
        html += `
                <button type="button" onclick="${functionName}(1);"><span>첫 페이지</span></button>
                <button type="button" onclick="${functionName}(${startPage - 1});"><span>이전</span></button>
               `;
    }

    for (let i = startPage; i <= endPage; i++) {
        html += `<button type="button" data-page="${i}" onclick="${functionName}(${i});">${i}</button>`;
    }

    const existNextPage = ((endPage * pageData.size) < pageData.totalElements);
    if(existNextPage){
        html += `
             <button type="button" onclick="${functionName}(${endPage + 1});"><span>다음</span></button>
             <button type="button" onclick="${functionName}(${pageData.totalPages});"><span>마지막 페이지</span></button>
             `;
    }

    html += ``


    const drawTarget = document.querySelector('.paging');
    drawTarget.innerHTML = html;
    const currentPageElement = drawTarget.querySelector(`button[data-page="${pageData.page}"]`);
    if (currentPageElement) {
        currentPageElement.classList.add('on');
    }
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
