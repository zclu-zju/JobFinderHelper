import {createRouter, createWebHashHistory} from 'vue-router'

const router = createRouter({
    history: createWebHashHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/osjobs',
            name: 'osjobs',
            component: () => import("@/views/osjobs/index.vue"),
            children: [
                {
                    path: 'random',
                    name: 'osjobsRandom',
                    component: props => import('@/views/osjobs/random.vue'),
                }
            ]
        },
        {
            path: '/problems',
            name: 'problems',
            meta: {title: "Jobs面经题库"},
            component: () => import("@/views/problems.vue")
        },
        {
            path: '/jobs',
            name: 'jobs',
            meta: {title: "Jobs招聘信息"},
            component: () => import("@/views/jobs.vue")
        },
        {
            name: 'index',
            redirect: '/problems',
        }
    ],
})

router.beforeEach((to, from, next) => {
    document.title = to.meta.title
    next();
})

export default router
