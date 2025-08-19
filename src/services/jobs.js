import api from "@/services/axios.js";

const baseUrl = 'jobs/';

export const get_jobs = (pagenum, pagesize) => {
    return api.get(baseUrl, {
        params: {
            pagenum: pagenum,
            pagesize: pagesize
        }
    })
}