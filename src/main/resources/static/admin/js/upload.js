// 파일 삭제 처리용 익명함수
const fileManager = (function() {
    let fileIndex = 0;
    const removedFileIds = [];

    return {
        increaseIndex() {
            return ++fileIndex;
        },
        getCurrentIndex() {
            return fileIndex;
        },
        addFiles(files) {
            for (let i = 0; i < files.length; i++) {
                this.increaseIndex(files[i]);
            }
        },
        addRemovedFileId(fileId) {
            if (removedFileIds.includes(fileId)) {
                return false;
            }
            removedFileIds.push(fileId);
        },
        getAllRemovedFileId() {
            return removedFileIds;
        },
    }
}());

// 파일 Drag & Drop 이벤트 추가
function addDragAndDropEvent(maximumFileLength) {
    if ( !maximumFileLength ) {
        maximumFileLength = 5;
    }

    const dropZone = getNode('#inner');

    dropZone.addEventListener('dragenter', (e) => { // Drag가 영역에 들어왔을 때
        e.stopPropagation();
        e.preventDefault();
        dropZone.style.backgroundColor = '#ecf0f9';
    });

    dropZone.addEventListener('dragover', (e) => { // Drag가 영역에 Over되어 있을 때
        e.stopPropagation();
        e.preventDefault();
        dropZone.style.backgroundColor = '#ecf0f9';
    });

    dropZone.addEventListener('dragleave', (e) => { // Drag가 영역을 벗어났을 때
        e.stopPropagation();
        e.preventDefault();
        dropZone.style.backgroundColor = '#FFFFFF';
    });

    dropZone.addEventListener('drop', (e) => { // Drag를 Drop 했을 때
        e.stopPropagation();
        e.preventDefault();
        dropZone.style.backgroundColor = '#FFFFFF';
        executeAllUploadProcess(e.dataTransfer.files)
    });
}

// 첨부파일 삭제
function removeFile(elementToRemove, fileId) {
    if (fileId) {
        fileManager.addRemovedFileId(fileId);
    }
    elementToRemove.remove();
}

function validFileSize() {
    let fileSize = 0;
    document.querySelectorAll('input[type=file]').forEach(value => {
        if (value.files[0] !== undefined) {
            fileSize += value.files[0].size;
        }
    });

    const byte128MB = 128_000_000;
    if (fileSize > byte128MB) {
        let message = '128MB 파일 용량을 초과했습니다.\n';
        message += '첨부파일 용량 : ' + bytesToSize(fileSize);
        alert(message);
        throw new Error(message);
    }
}

function bytesToSize(bytes) {
    var sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
    if (bytes == 0) return 'n/a';
    var i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
    return Math.round(bytes / Math.pow(1024, i), 2) + ' ' + sizes[i];
}

function fileLengthValidation(newFiles, maximumFileLength) {
    const totalFileLength = getAllNode('input[name="images"]').length;
    if ((totalFileLength + newFiles.length) > maximumFileLength) {
        alert(`파일은 최대 ${maximumFileLength}개까지 업로드가 가능합니다.`);
        throw new Error();
    }
}

function executeAllUploadProcess(files, maximumFileLength) {
    imageValidation(files);
    fileLengthValidation(files, maximumFileLength);
    fileManager.addFiles(files);
    appendFilesHtml(files);
}

function uploadImages(element) {
    const files = element.files;
    if (files.length) {
        executeAllUploadProcess(files, 10);
    }
}

function appendFilesHtml(files) {
    const currentFileIndex = fileManager.getCurrentIndex();
    const imagesDiv = getNode('#images');

    for (let i = 0, len = files.length; i < len; i++) {
        const dataTransfer = new DataTransfer();
        const div = document.createElement('div');
        const index = currentFileIndex - len + (i + 1);
        dataTransfer.items.add(files[i]);
        div.className = 'item';
        div.innerHTML = fileHtml(files[i], index);
        div.appendChild(makeImageInputElement(dataTransfer.files, index));
        imagesDiv.append(div);
    }

    $(imagesDiv).sortable({ stop: (event, ui) => reordering() });
    $(imagesDiv).disableSelection();
}

function fileHtml(file, index) {
    const dataTransfer = new DataTransfer();
    dataTransfer.items.add(file);
    return `
        <div class="thumb_wrap"><img src="${URL.createObjectURL(file)}" alt="상품 이미지${index}" /></div>
        <button type="button" class="del_btn material-icons" onclick="removeFile(this.closest('.item'));"><i>삭제</i><span aria-hidden="true">cancel</span></button>
    `;
}

function makeImageInputElement(files, index) {
    const input = document.createElement('input');
    input.type = 'file';
    input.name = 'images';
    input.accept = 'image/*';
    input.required = true;
    input.hidden = true;
    input.dataset.index = index;
    input.files = files;
    return input;
}

function imageValidation(files) {
    for (let i = 0; i < files.length; i++) {
        if ( !isImageFile(files[i].name) ) {
            alert('jpg, jpeg, png 파일로 업로드해 주세요.');
            throw new Error();
        }
    }
}

function reordering() {
    const files = getAllNode('input[name="images"]');
    files.forEach((input, i) => {
        input.dataset.index = i + 1;
        input.closest('.item').firstElementChild.firstElementChild.alt = `상품 이미지${i + 1}`;
    });
}
