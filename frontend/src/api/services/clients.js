import { BASE_URL, HTTP } from "../http";
import getToken from "@/api/utils";

export default {
    getAll() {
        return HTTP.get(BASE_URL + "/clients/find-all", { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    deleteById(Id) {
        return HTTP.delete(BASE_URL + "/clients/" + Id, { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    create(PatientData) {
        return HTTP.post(BASE_URL + "/clients/save", PatientData, { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    update(PatientData) {
        return HTTP.patch(BASE_URL + "/clients/update", PatientData, { headers: getToken() }).then((response) => {
            return response.data;
        });
    }
};