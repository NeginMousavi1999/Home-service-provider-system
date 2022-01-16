function expertFunction() {
    document.getElementById("expert_info").style.display = "block";
}

function customerFunction() {
    document.getElementById("expert_info").style.display = "none";
}

function fileValidation() {
    const fileInput =
        document.getElementById("formFileSm");
    const filePath = fileInput.value;
    const allowedExtensions =
        /(\.jpg|\.jpeg|\.png|\.gif)$/i;
    if (!allowedExtensions.exec(filePath)) {
        alert('Invalid file type');
        fileInput.value = '';
        return false;
    }
}