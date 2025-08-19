<script setup>
import {RouterLink, RouterView, useRoute, useRouter} from 'vue-router'
import {ref, watch, onBeforeMount, onBeforeUnmount} from "vue";
import SwitchDarkButton from "@/components/SwitchDarkButton.vue";

const route = useRoute();
const router = useRouter();
const activeIndex = ref(route.path);
watch(route, (newRoute) => {
  activeIndex.value = newRoute.path;
})
const changeActiveIndex = (path) => {
  router.push(path);
}
const innerWidth = ref(window.innerWidth);

onBeforeMount(() => {
  window.addEventListener("resize", () => {
    innerWidth.value = window.innerWidth;
  })
})
onBeforeUnmount(() => {
  window.removeEventListener("resize", () => {
  })
})
</script>

<template>
  <el-container>
    <el-header style="padding: 0; width: 100%;">
      <el-menu
          mode="horizontal"
          :default-active="activeIndex"
          style="align-items: center;"
          :ellipsis="innerWidth <= 600"
      >
        <el-menu-item index="/problems" @click="changeActiveIndex('problems')">
          题库
        </el-menu-item>
        <el-menu-item index="/jobs" @click="changeActiveIndex('jobs')">
          招聘信息
        </el-menu-item>
        <!--        <el-menu-item style="flex: 1;"></el-menu-item>-->
        <el-menu-item>
          <el-link type="primary" :underline="false" target="_blank"
                   href="https://scholar.google.com/citations?user=byXIEVMAAAAJ&hl=en">
            Google Scholar
          </el-link>
        </el-menu-item>
        <el-menu-item>
          <el-link
              href="https://zcluu.github.io/#/"
              target="_blank"
              :underline="false"
          >HomePage
          </el-link>
        </el-menu-item>
        <el-menu-item>
          <el-link href="mailto:zclu@zju.edu.cn" :underline="false">Contact Me</el-link>
        </el-menu-item>
        <div style="padding-right: 1em;">
          <SwitchDarkButton/>
        </div>
      </el-menu>
    </el-header>
    <el-main>
      <RouterView :key="$route.fullPath"/>
    </el-main>
  </el-container>
</template>

<style scoped>
.el-menu--horizontal > .el-menu-item:nth-child(2) {
  margin-right: auto;
}
</style>
