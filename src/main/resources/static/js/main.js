function pagination(currentPage, totalPage){
    let current = currentPage,
        last = totalPage,
        delta = 2,
        left = current - delta,
        right = current + delta,
        range = [],
        rangeWithDots = [],
        prevPage;

    for (let i = 1; i <= last ; i++) {
        if(i === 1 || i === last || (i >= left && i <= right) || ((current === 1 || current === 2) && (i === 4 || i === 5))){
            range.push(i);
        }
    }

    for (const i of range) {
        if(prevPage) {
            if(i - prevPage === 2){
                rangeWithDots.push(prevPage + 1);
            }else if(i - prevPage !== 1){
                rangeWithDots.push("...");
            }
        }
        rangeWithDots.push(i);
        prevPage = i;
    }
    return rangeWithDots;
}

// function renderPagination(currentPage, totalPage){
//     const pages = pagination(currentPage, totalPage);
//     let html = `<li class="page-item ${currentPage === 1 ? "disabled" : ""}">
//                 <a class="page-link" href="#" aria-label="Previous" onclick="renderPagination(${currentPage - 1}, ${totalPage})">
//                     <span aria-hidden="true">&laquo;</span>
//                 </a>
//             </li>`;
//     for (const i of pages) {
//         html += `<li class="page-item ${currentPage === i ? "active" : ""}"><a class="page-link" href="#" onclick="renderPagination(${i}, ${totalPage})">${i}</a></li>`
//     }
//     html += `<li class="page-item ${currentPage === totalPage ? "disabled" : ""}">
//             <a class="page-link" href="#" aria-label="Next" onclick="renderPagination(${currentPage + 1}, ${totalPage})">
//                 <span aria-hidden="true">&raquo;</span>
//             </a>
//         </li>`
//
//     $(".pagination").html(html);
// }