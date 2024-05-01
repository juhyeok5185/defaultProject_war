// $(document).ready(function(){
// 	// 첨부파일
// 	$(".file_input input[type='file']").on('change',function(){
// 		var fileName = $(this).val().split('/').pop().split('\\').pop();
// 		$(this).parent().siblings("input[type='text']").val(fileName);
// 		var tmppath = URL.createObjectURL(event.target.files[0]);
// 		$(this).parent('label').parent('.file_input').prev('.imgs').find("img").attr('src',tmppath);
// 	});
// });

//레이어 팝업(기본)
function layerPop(popName) {
    var $layer = $("#" + popName);
    $layer.fadeIn(500).css('display', 'inline-block').wrap('<div class="overlay_t"></div>');
    $('body').css('overflow', 'hidden');
}

function layerPopClose() {
    $(".overlay_t").children().hide().unwrap('');
    $('body').css('overflow', 'auto');
}

function layerPopClose2(popName) {
    $("#" + popName).hide().unwrap('');
    $('body').css('overflow', 'auto');
}

function setDatepickerOptions(yearRange) {
    const monthNames = ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'];
    const dayNames = ['일', '월', '화', '수', '목', '금', '토'];
    $.datepicker.setDefaults({
        dateFormat: 'yy-mm-dd',
        showOn: 'both',
        buttonImage: '/static/admin/images/day_icon.png',
        prevText: '이전 달',
        nextText: '다음 달',
        monthNames: monthNames,
        monthNamesShort: monthNames,
        dayNames: dayNames,
        dayNamesShort: dayNames,
        dayNamesMin: dayNames,
        showMonthAfterYear: true,
        yearSuffix: '년',
        buttonImageOnly: true,
        yearRange: (yearRange || '-80:+5'),
        changeMonth: true,
        changeYear: true,
    });
}

// datepicker 적용
function applyDatepicker(selector, yearRange) {
    setDatepickerOptions(yearRange);
    $(selector || '.dateVal').datepicker();
}

// datetimepicker 적용
function applyDatetimepicker(selector) {
    const allowTimes = [];
    for (let i = 9; i < 24; i++) {
        const hour = (i > 9) ? i : ('0' + i);
        allowTimes.push(hour + ':00'.trim());
        allowTimes.push(hour + ':30'.trim());
    }

    $(selector || 'input[data-field-type="time"]').datetimepicker({
        datepicker: false,
        format: 'H:i',
        allowTimes: allowTimes
    });
}


//주혁 file
function addFile2() {
    const files = getAllNode('input[name="productFiles"]');
    if (files.length > 4) {
        alert('첨부파일은 최대 5개까지 등록할 수 있습니다.');
        return false;
    }

    const nextFileNumber = Number(files[files.length - 1].dataset.fileNumber) + 1;
    const li = document.createElement('li');
    li.innerHTML = `
					<div class="file_input">
						<input type="text" id="filename${nextFileNumber}" readonly />
						<label> 첨부파일
							<input type="file" id="file${nextFileNumber}" name="productFiles" onchange="uploadFile2(this);" data-file-number="${nextFileNumber}" />
						</label>
					</div>
					<button type="button" class="btns primary" onclick="removeFile2(this);">
					    <i class="skip_info">삭제</i>
					    <span class="material-icons-outlined" aria-hidden="true">remove</span>
					</button>
				`;

    getNode('.file_list').appendChild(li);
}


function removeFile2(element) {
    const fileAddBtn = element.nextElementSibling;
    if (!fileAddBtn) {
        element.parentElement.remove();
        return false;
    }

    const inputs = element.parentNode.getAllNode('input');
    inputs.forEach(input => input.value = '');
}

function removeInitFirstFile2(element) {
    getNode('#filename0').value = '';
}

function removeFirstFile2(element) {
    getNode('#filename0').value = '';

    const newLabel = document.createElement('label');
    newLabel.id = 'firstLabel';
    newLabel.innerHTML = `
                        첨부파일
                        <input type="file" id="file0" name="productFiles" onchange="uploadFile2(this)" data-file-number="0"/>
                            `;

    document.getElementById('firstLabel').replaceWith(newLabel);

}

function uploadFile2(file) {
    const filename = file.closest('div').firstElementChild;
    uploadValidation(file, filename);
}



function addFile() {
    const files = getAllNode('input[name="files"]');
    if (files.length > 4) {
        alert('첨부파일은 최대 5개까지 등록할 수 있습니다.');
        return false;
    }

    const nextFileNumber = Number(files[files.length - 1].dataset.fileNumber) + 1;
    const li = document.createElement('li');
    li.innerHTML = `
					<div class="file_input">
						<input type="text" id="filename${nextFileNumber}" readonly />
						<label> 첨부파일
							<input type="file" id="file${nextFileNumber}" name="files" onchange="uploadFile2(this);" data-file-number="${nextFileNumber}" />
						</label>
					</div>
					<button type="button" class="btns primary" onclick="removeFile2(this);">
					    <i class="skip_info">삭제</i>
					    <span class="material-icons-outlined" aria-hidden="true">remove</span>
					</button>
				`;

    getNode('.file_list').appendChild(li);
}


function removeFile(element) {
    const fileAddBtn = element.nextElementSibling;
    if (!fileAddBtn) {
        element.parentElement.remove();
        return false;
    }

    const inputs = element.parentNode.getAllNode('input');
    inputs.forEach(input => input.value = '');
}

function removeInitFirstFile(element) {
    getNode('#filename0').value = '';
}

function removeFirstFile(element) {
    getNode('#filename0').value = '';

    const newLabel = document.createElement('label');
    newLabel.id = 'firstLabel';
    newLabel.innerHTML = `
                        첨부파일
                        <input type="file" id="file0" name="files" onchange="uploadFile(this)" data-file-number="0"/>
                            `;

    document.getElementById('firstLabel').replaceWith(newLabel);

}

function uploadFile(file) {
    const filename = file.closest('div').firstElementChild;
    uploadValidation(file, filename);
}
