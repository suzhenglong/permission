
$(document).ready(function () {
    $(".popstyle").removeAttr("target");
    $(".popstyle").each(function () {
        var $this = $(this)
        tmp = $this.attr("href");
        $this.attr("data", tmp);
        $this.attr("href", "javascript:void(0)");
    })

    $(".popstyle").click(function () {
        var $this = $(this)
        $("iframe").attr(
            'src',
            $this.attr("data")
        );
    });

    $(".direct").click(function () {
        var $this = $(this)
        $("iframe").attr(
            'src',
            $this.attr("data-value")
        );
    });
});