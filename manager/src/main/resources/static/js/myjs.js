// 클래스 추가
function addClassName(elementId, name) {
    document.getElementById(elementId).className += ' ' + name;
}

function removeClassName(elementId, name) {
    document.getElementById(elementId).classList.remove(name);
}

function deleteExampleConfirm(elementId) {
    if (confirm("정말 삭제하시겠습니까?")) {
        document.getElementById(elementId).textContent = '';
    }
}

function addRow() {
    // 먼저 저장한 뒤, 새로운 list에 string을 추가해서 다시 loading 하는 방법으로 가야함
    if (confirm("계속 진행하기 위해서 현 상태를 저장합니까?")) {
        document.getElementById('form').submit();
    }
}

function toggleDisabled(elementId) {
    const target = document.getElementById(elementId);
    if (target.getAttribute('disabled') == null) {
        target.disabled = true;
    } else {
        target.disabled = false;
    }
}

// // Dealing with Textarea Height
// function calcHeight(value) {
//     let numberOfLineBreaks = (value.match(/\n/g) || []).length;
//     // min-height + lines x line-height + padding + border
//     let newHeight = 20 + numberOfLineBreaks * 20 + 12 + 2;
//     return newHeight;
// }
//
// let textarea = document.querySelector(".resize-ta");
// textarea.addEventListener("keyup", () => {
//     textarea.style.height = calcHeight(textarea.value) + "px";
// });