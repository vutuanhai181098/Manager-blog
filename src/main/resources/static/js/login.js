const showPassEl = document.getElementById('show-password');
const passwordInputEl = document.getElementById('password');
showPassEl.addEventListener('change', () => {
    if (showPassEl.checked) {
        passwordInputEl.type = 'text';
        showPassEl.nextElementSibling.style.color = "#fff";
    } else {
        passwordInputEl.type = "password";
        showPassEl.nextElementSibling.style.color = "#8f8f8f";
    }
});