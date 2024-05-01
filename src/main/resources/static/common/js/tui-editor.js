function initEditor(selector) {
    const editor = new toastui.Editor({
        el: getNode(selector),   // 에디터를 적용할 요소 (컨테이너)
        width: 'auto',
        height: '500px',                        // 에디터 영역의 높이 값 (OOOpx || auto)
        initialEditType: 'wysiwyg',             // 최초로 보여줄 에디터 타입 (markdown || wysiwyg)
        previewStyle: 'vertical',               // 마크다운 프리뷰 스타일 (tab || vertical)
        placeholder: '내용을 입력해 주세요.',
        language: 'ko',
        hooks: { /* start of hooks */
            async addImageBlobHook(blob, callback) { // 이미지 업로드 로직 커스텀
                try {
                    const formData = new FormData();
                    formData.set('image', blob);

                    const ajaxOption = getPostFileOption('/api/files/image-upload', formData);
                    const response = await $.ajax(ajaxOption).fail(executeErrorMessage);
                    const imageUrl = `/api/files/image-print?filename=${response}&target=EDITOR`;
                    callback(imageUrl, `에디터 이미지 ${blob.name}`);

                } catch (error) {
                    console.error('업로드 실패 : ', error);
                }
            }
        } /* end of hooks */
    });

    return editor;
}

function editorValidation(html, fieldName) {
    if (html.trim() !== '<p><br></p>') {
        return true;
    }
    const particle = hasCoda(fieldName) ? '을' : '를';
    const inputTypes = ['text', 'textarea', 'number', 'search', 'password', 'email', 'tel'];
    alert(fieldName + particle + ' ' + '입력해 주세요.');
    getNode('.ProseMirror.toastui-editor-contents').focus();
    throw new Error();
}
