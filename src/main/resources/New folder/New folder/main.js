const easyMDE = new EasyMDE({
    element: document.getElementById("content"),
    spellChecker: false,
    maxHeight: "500px",
    renderingConfig: {
        codeSyntaxHighlighting: true,
    }
})

$(document).ready(function () {
    $('.select2').select2({
        placeholder: '---Chọn danh mục',
        allowClear: true
    });
});