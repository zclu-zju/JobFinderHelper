import api from './axios'

const baseUrl = 'osjobs/'

export const get_question_random = () => {
    return api.get(baseUrl + 'random')
}

export const get_question_by_id = (id) => {
    return api.get(baseUrl + id)
}

export const get_questions_all = () => {
    return api.get(baseUrl + 'all')
}

export const get_questions_by_tag = (tag) => {
    return api.get(baseUrl, {params: {tag: tag}})
}