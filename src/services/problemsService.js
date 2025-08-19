import api from "@/services/axios.js";

const baseUrl = "problems/";

export const get_problem_random = () => {
    return api.get(baseUrl + 'random')
}

export const search_problem = (kw, tag, page, pagesize) => {
    console.log(kw, tag, pagesize, page)
    return api.get(baseUrl + 'search', {
        params: {
            kw: kw,
            tag: tag,
            page: page,
            pagesize: pagesize,
        }
    })
}

export const update_problem_tag = (id, tag) => {
    return api.post(baseUrl + 'tag/' + id + '/' + tag, {})
}

export const create_problem = (question, source) => {
    return api.post(baseUrl + 'create', {
        question: question,
        source: source
    })
}