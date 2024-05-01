window.addEventListener('load', async () => {
    menus.draw();
});

/**
 * left menu 출력
 */
const menus = (function () {

    const menus = [
        {
            name: '홈',
            uri: 'dashboard',
            icon: 'apps',
            children: [],
        },
        {
            name: '카테고리',
            uri: 'category',
            icon: 'category',
            children: [
                { name: '대분류', uri: 'first' },
                { name: '중분류', uri: 'second' },
                { name: '소분류', uri: 'third' },
            ],
        },
        {
            name: '상품',
            uri: 'product',
            icon: 'inventory_2',
            children: [
                { name: '상품 관리', uri: 'list' },
                { name: '상품 등록', uri: 'write' },
                { name: '상품평 관리', uri: 'review' },
            ],
        },
        {
            name: '주문',
            uri: 'order',
            icon: 'shopping_cart',
            children: [
                { name: '주문 관리', uri: 'list' },
                { name: '주문 취소 관리', uri: 'cancelList' },
                { name: '반품 관리', uri: 'returnList' },
                { name: '교환 관리', uri: 'changeList' },
                { name: '정산 관리', uri: 'calList' },
                { name: '배송 관리', uri: 'delList' },
            ],
        },
        {
            name: '사용자',
            uri: 'person',
            icon: 'person',
            children: [
                { name: '회원 관리', uri: 'memberList' },
                { name: '관리자 관리', uri: 'adminLIst' },
            ],
        },
        {
            name: '게시판',
            uri: 'board',
            icon: 'assignment',
            children: [
                { name: '공지사항', uri: 'notice', pageType: 'list' },
                { name: '자료실', uri: 'file-board', pageType: 'list' },
                { name: '문의게시판', uri: 'forum', pageType: 'list' },
                { name: '1:1 문의', uri: 'qna', pageType: 'list' },
                { name: 'FAQ', uri: 'FAQ', pageType: 'list' },
                { name: '입점제휴문의', uri: 'join-member', pageType: 'list' },
            ],
        },
        {
            name: '사이트 설정',
            uri: 'setting',
            icon: 'settings',
            children: [
                { name: '배너 관리', uri: 'banner' },
                { name: '팝업 관리', uri: 'popup' },
                { name: '메인 상품 개수 설정', uri: 'main-goods' },
                { name: '회사 정보 관리', uri: 'company-info' },
                { name: '택배사 관리', uri: 'delivery', pageType: 'list' }
            ],
        },
        {
            name: '통계',
            uri: 'bar_chart',
            icon: 'bar_chart',
            children: [
                { name: '호스트 로그', uri: 'curriculum' },
                { name: '통계', uri: 'curriculum' },
            ],
        },
    ];

    return {
        draw() {
            const uriParts = location.pathname.split('/');
            drawLeftMenus(menus, uriParts);
            addMenuClickEvent();
            activeMenu();
        }
    }
}());

function drawLeftMenus(menus, uriParts) {
    let html = '';

    menus.forEach(firstMenu => {
        // 자식 메뉴가 없는 대메뉴
        if ( !firstMenu.children.length ) {
            html += `<li data-menu-uri="${firstMenu.uri}"><a href="/AD9000/${firstMenu.uri}"><i class="material-icons" aria-hidden="true">${firstMenu.icon}</i> <span>${firstMenu.name}</span></a></li>`
            return false;
        }

        // 자식 메뉴가 있는 대메뉴
        html += `<li data-menu-uri="${firstMenu.uri}" class="has_sub"><a href="javascript:void(0);"><i class="material-icons" aria-hidden="true">${firstMenu.icon}</i> <span>${firstMenu.name}</span></a><ul>`;
        firstMenu.children.forEach(secondMenu => {
            const queryString = (secondMenu.pageType) ? `?pageType=${secondMenu.pageType}` : '';
            const menuFullUri = `/AD9000/${firstMenu.uri}/${secondMenu.uri}` + queryString;
            html += `<li data-menu-uri="${secondMenu.uri}"><a href="${menuFullUri}">${secondMenu.name}</a></li>`;
        });
        html += `</ul></li>`;
    });

    getNode('#gnb').innerHTML = html;
}

function activeMenu() {
    const uriParts = location.pathname.split('/');
    const firstMenu = getNode(`li[data-menu-uri="${uriParts[2]}"]`);

    if ( !firstMenu.classList.contains('has_sub') ) {
        firstMenu.firstElementChild.classList.add('on');
        return false;
    }

    const secondMenu = firstMenu.getNode(`li[data-menu-uri="${uriParts[3]}"]`);
    firstMenu.firstElementChild.click();
    secondMenu.firstElementChild.classList.add('on');
}

function addMenuClickEvent() {
    $("#gnb > li > a").click(function (e) {
        if ($(this).parent().hasClass("has_sub")) {
            e.preventDefault();
        }

        if ($(this).hasClass("on")) {
            $(this).removeClass("on");
            $(this).next("ul").slideUp();
            return false;
        }

        $("#gnb li ul").slideUp();
        $("#gnb > li > a").removeClass("on");
        $(this).next("ul").slideDown();
        $(this).addClass("on");
    });
}
