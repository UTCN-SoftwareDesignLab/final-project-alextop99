import { BASE_URL, HTTP } from "../http";
import getToken, {saveFile} from "@/api/utils";

export default {
    getAll() {
        return HTTP.get(BASE_URL + "/appointments/find-all", { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    getAllByDateAndMechanic(date, mechanicId) {
        return HTTP.get(BASE_URL + "/appointments/find-all-by-date-and-mechanic" + "?date=" + date + "&id=" + mechanicId, { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    getReceipt(AppointmentData) {
        return HTTP.post(BASE_URL + "/appointments/receipt/PDF", AppointmentData, { responseType: 'blob', headers: getToken() }).then((response) => {
            if(response.data.size > 9) {
                saveFile("PDF", response);
            }
            else {
                console.log("Receipt generation has failed! Please contact an ADMIN!");
            }
            return response.data;
        });
    },
    getAllByCar(carId, datestring) {
        return HTTP.get(BASE_URL + "/appointments/find-all-by-car" + "?car=" + carId + "&date=" + datestring, { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    deleteById(Id) {
        return HTTP.delete(BASE_URL + "/appointments/" + Id, { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    create(AppointmentData) {
        return HTTP.post(BASE_URL + "/appointments/save", AppointmentData, { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    update(AppointmentData) {
        return HTTP.patch(BASE_URL + "/appointments/update", AppointmentData, { headers: getToken() }).then((response) => {
            return response.data;
        });
    }
};