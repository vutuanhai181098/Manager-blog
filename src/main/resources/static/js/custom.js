// show time
const showTimeEl = document.querySelector('.show-time span');

const showTime = () => {
    const currentTime = new Date();
    const daysOfWeek = ["Chủ nhật", "Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm", "Thứ sáu", "Thứ bảy"];

    const day = daysOfWeek[currentTime.getDay()];
    const yyyy = currentTime.getFullYear();
    let MM = currentTime.getMonth() + 1;
    let dd = currentTime.getDate();
    let HH = currentTime.getHours();
    let mm = currentTime.getMinutes();

    // format date time
    dd = (dd < 10 ? "0" : "") + dd;
    MM = (MM < 10 ? "0" : "") + MM;
    HH = (HH < 10 ? "0" : "") + HH;
    mm = (mm < 10 ? "0" : "") + mm;

    showTimeEl.textContent = day + ", " + dd + "/" + MM + "/" + yyyy + " - " + HH + " : " + mm;
}

setInterval(showTime, 1000);

// pagination
function pagination(currentPage, totalPage){
    let current = currentPage,
        last = totalPage,
        delta = 2,
        left = current - delta,
        right = current + delta,
        range = [],
        rangeWithDots = [],
        prevPage;

    for (let i = 1; i <= last; i++) {
        if (i === 1 || i === last || (i >= left && i <= right)
            || ((current === 1 || current === 2) && (i === 4 || i === 5))) {
            range.push(i);
        }
    }

    for (let i of range) {
        if (prevPage) {
            if (i - prevPage === 2) {
                rangeWithDots.push(prevPage + 1);
            } else if (i - prevPage !== 1) {
                rangeWithDots.push("...");
            }
        }
        rangeWithDots.push(i);
        prevPage = i;
    }
    return rangeWithDots;
}
// custom notification
const Notification1 = Swal.mixin({
    toast: true,
    position: 'top-end',
    showConfirmButton: false,
    timer: 1000,
    timerProgressBar: true,
});

const Notification2 = Swal.mixin({
    toast: true,
    position: 'top',
    showConfirmButton: true
});
// custom confirm
const Confirm = Swal.mixin({
    position: 'top',
    showCancelButton: true,
    reverseButtons: true,
    buttonsStyling: false,
    customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
    }
})

// active navbar
const activeNavbar = () => {
    const navbarItemEls = document.querySelectorAll('.navbar a.nav-link');
    let path = window.location.pathname;
    navbarItemEls.forEach(item => {
        if (item.getAttribute('href') === path){
            item.classList.add('active')
        }
    })
}
activeNavbar();


// function copy data table to clipboard
function copyDataTableToClipboard(tableId) {
   const tableEl = document.getElementById(tableId);
   let tableData = "";

    // Lặp qua từng row trong bảng

    for(let i = 0; i < tableEl.rows.length; i++){
        let rowData = [];
        let row = tableEl.rows[i];

        // Lặp qua từng cell trong bảng
        for(let j = 0; j < row.cells.length; j++){
            let cell = row.cells[j];
            rowData.push(cell.innerText);
        }

        // Ghép các giá trị của ô thành một dòng dữ liệu
        let rowText = rowData.join("\t");
        tableData += rowText + "\n";
    }

    // Tạo một phần tử tạm thời (temporary element)
    let tempElement = document.createElement("textarea");
    tempElement.value = tableData;

    // Gắn phần tử tạm thời vào body để có thể lấy giá trị
    document.body.appendChild(tempElement);

    // Chọn và sao chép giá trị trong phần tử tạm thời vào clipboard
    tempElement.select();
    document.execCommand("copy");

    // Xóa phần tử tạm thời khỏi body
    document.body.removeChild(tempElement);

    console.log("Hello")
}
