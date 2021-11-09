import { createWebHistory, createRouter } from "vue-router";
import Home from "@/views/Home.vue";
import About from "@/views/About.vue";

const routes = [
    {
        path:"/",
        name:"Home",
        component: Home,
    },
    {
        path:"/About",
        name:"Home",
        component: About,
    }
];

const router = createRouter({
    history : createWebHistory(),
    routes,
});
export default router;

/* export default new Router({
    mode: 'history',
    routes:[{
        path:"/",
        alias:'/user/all',
        name:'user',
        component: ()=>import('./components/UserList')
    },
    {
        path:'/user/:id',
        name: 'edit-customer',
        component:()=>import('./components/EditUser')
    }
]
}) */