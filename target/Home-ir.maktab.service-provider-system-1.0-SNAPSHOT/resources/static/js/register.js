function expertFunction() {
    document.getElementById("expert_info").style.display = "block";
}

function customerFunction() {
    document.getElementById("expert_info").style.display = "none";
}

function fileValidation() {
    const fileInput = document.getElementById("formFileSm");

    const filePath = fileInput.value;
    const allowedExtensions =
        /(\.jpg|\.jpeg|\.png|\.gif)$/i;
    if (!allowedExtensions.exec(filePath)) {
        alert('Invalid file type');
        fileInput.value = '';
    }

    const maxAllowedSize = 300 * 1024;
    if (fileInput.files[0].size > maxAllowedSize) {
        alert('Image File is too big');
        fileInput.value = '';
    }
}

/*
const image = document.getElementById("formFileSm");
image.onchange = function () {
    const maxAllowedSize = 300 * 1024;
    if (this.files[0].size > maxAllowedSize) {
        alert('Image File is too big');
        this.value = "";
    }
    const filePath = image.value;
    const allowedExtensions =
        /(\.jpg|\.jpeg|\.png|\.gif)$/i;
    if (!allowedExtensions.exec(filePath)) {
        alert('Invalid file type');
        image.value = '';
        return false;
    }
}*/
