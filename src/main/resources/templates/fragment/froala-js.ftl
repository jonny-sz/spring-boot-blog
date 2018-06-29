<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.25.0/codemirror.min.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.25.0/mode/xml/xml.min.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.8.4/js/froala_editor.pkgd.min.js"></script>
<script>
    $(function () {
        $('#editor').froalaEditor({
            toolbarButtons: [
                'fullscreen',
                'bold',
                'italic',
                'underline',
                'strikeThrough',
                'subscript',
                'superscript',
                'fontFamily',
                'fontSize',
                '|',
                'color',
                'emoticons',
                'paragraphStyle',
                '|',
                'paragraphFormat',
                'align',
                'formatOL',
                'formatUL',
                'outdent',
                'indent',
                '-',
                'insertLink',
                'insertTable',
                '|',
                'quote',
                'insertHR',
                'undo',
                'redo',
                'clearFormatting',
                'selectAll',
                'html'
            ],
            height: 300,
            placeholderText: 'Статья',
            pasteDeniedTags: ['script']
        })
    });
</script>
