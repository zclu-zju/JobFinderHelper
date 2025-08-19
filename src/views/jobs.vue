<script>
import {onMounted, ref, watch} from "vue";
import {useRoute} from "vue-router";
import {get_jobs} from "@/services/jobs.js";
import router from "@/router/index.js";

export default {
  name: "jobs",
  setup() {
    const jobs = ref([]);
    const pagenum = ref(1);
    const pagesize = ref(10);
    const loading = ref(true);
    const route = useRoute();
    if (route.query && route.query.pagesize) {
      pagesize.value = parseInt(route.query.pagesize, 10);
    }
    if (route.query && route.query.pagenum) {
      pagenum.value = parseInt(route.query.pagenum, 10);
    }
    const fetchJobs = async () => {
      console.log('fetch jobs');
      jobs.value = [];
      try {
        const response = await get_jobs(pagenum.value, pagesize.value);
        const jobs_data = response.data;
        for (const job of jobs_data) {
          jobs.value.push(job);
        }
      } catch (error) {
        console.log(error);
      } finally {
        loading.value = false;
      }
    }
    const pageChanged = () => {
      router.push({
        name: "jobs",
        query: {
          pagenum: pagenum.value,
          pagesize: pagesize.value
        }
      })
    }
    onMounted(() => {
      fetchJobs();
    })
    watch(() => route.query.pagesize, () => fetchJobs, {immediate: true});
    watch(() => route.query.pagenum, () => fetchJobs, {immediate: true});
    watch(() => pagesize, () => fetchJobs, {immediate: true});
    watch(() => pagenum, () => fetchJobs, {immediate: true});
    return {
      pageChanged,
      jobs,
      pagenum,
      pagesize,
    }
  }
}
</script>

<template>
  <el-row justify="center">
    <el-col :xs="24" :sm="24" :md="20">
      <el-table :data="jobs" border stripe>
        <el-table-column prop="id" label="id" width="50">
          <template #header>
            <div style="text-align: center">ID</div>
          </template>
          <template #default="scope">
            <div style="text-align: center"> {{ scope.$index + pagesize * (pagenum - 1) + 1 }}</div>
          </template>
        </el-table-column>
        <el-table-column prop="title" label="标题">
          <template #default="scope">
            <el-text truncated> {{ scope.row.title }}</el-text>
          </template>
        </el-table-column>
        <el-table-column prop="url" label="链接">
          <template #default="scope">
            <el-link :href="scope.row.url">{{ scope.row.url }}</el-link>
          </template>
        </el-table-column>
        <el-table-column prop="date" label="日期" width="120">
          <template #header>
            <div style="text-align: center;">日期</div>
          </template>
          <template #default="scope">
            <div style="text-align: center"> {{ scope.row.date }}</div>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          style="margin-top: 10px"
          layout="prev, pager, next"
          v-model:current-page="pagenum"
          v-model:page-size="pagesize"
          @current-change="pageChanged"
          :total="300"
      ></el-pagination>
    </el-col>
  </el-row>
</template>

<style scoped>

</style>