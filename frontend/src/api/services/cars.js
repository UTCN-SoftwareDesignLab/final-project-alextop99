import {BASE_URL, HTTP} from "../http";
import getToken from "@/api/utils";

export default {
    getAll() {
        return HTTP.get(BASE_URL + "/cars/find-all", { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    deleteById(Id) {
        return HTTP.delete(BASE_URL + "/cars/" + Id, { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    create(CarsData) {
        return HTTP.post(BASE_URL + "/cars/save", CarsData, { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    update(CarsData) {
        return HTTP.patch(BASE_URL + "/cars/update", CarsData, { headers: getToken() }).then((response) => {
            return response.data;
        });
    }
};