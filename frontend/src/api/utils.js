import { saveAs } from 'file-saver';

export default function getToken() {
    let user = JSON.parse(localStorage.getItem("user"));
    if (user) {
        return { Authorization: "Bearer " + user.token };
    }
    return {};
}

export function saveFile(extension, response) {
    let currentDate = new Date();
    let datetime = currentDate.getDate() + "-"
        + (currentDate.getMonth()+1)  + "-"
        + currentDate.getFullYear() + " "
        + currentDate.getHours() + "-"
        + currentDate.getMinutes() + "-"
        + currentDate.getSeconds();
    if (extension === "PDF") {
        let blob = new Blob([response.data], {type: "application/pdf"});
        saveAs(blob, datetime + ".pdf");
    }
    else {
        let blob = new Blob([response.data], {type: "text/csv;charset=utf-8"});
        saveAs(blob, datetime + ".csv");
    }
}