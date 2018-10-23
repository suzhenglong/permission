var paginateTemplate = $("#paginateTemplate").html();
Mustache.parse(paginateTemplate);

function renderPage(url, total, pageNo, pageSize, currentSize, idElement, callback) {
    var maxPageNo = Math.ceil(total / pageSize);
    var paramStartChar = url.indexOf("?") > 0 ? "&" : "?";
    var from = (pageNo - 1) * pageSize + 1;
    var view = {
        from: from > total ? total : from,
        to: (from + currentSize - 1) > total ? total : (from + currentSize - 1),
        total : total,
        pageNo : pageNo,
        maxPageNo : maxPageNo,
        nextPageNo: pageNo >= maxPageNo ? maxPageNo : (pageNo + 1),
        beforePageNo : pageNo == 1 ? 1 : (pageNo - 1),
        firstUrl : (pageNo == 1) ? '' : (url + paramStartChar + "pageNo=1&pageSize=" + pageSize),
        beforeUrl: (pageNo == 1) ? '' : (url + paramStartChar + "pageNo=" + (pageNo - 1) + "&pageSize=" + pageSize),
        nextUrl : (pageNo >= maxPageNo) ? '' : (url + paramStartChar + "pageNo=" + (pageNo + 1) + "&pageSize=" + pageSize),
        lastUrl : (pageNo >= maxPageNo) ? '' : (url + paramStartChar + "pageNo=" + maxPageNo + "&pageSize=" + pageSize)
    };
    $("#" + idElement).html(Mustache.render(paginateTemplate, view));

    $(".page-action").click(function(e) {
        e.preventDefault();
        $("#" + idElement + " .pageNo").val($(this).attr("data-target"));
        var targetUrl  = $(this).attr("data-url");
        if(targetUrl != '') {
            $.ajax({
                url : targetUrl,
                success: function (result) {
                    if (callback) {
                        callback(result, url);
                    }
                }
            })
        }
    })
}