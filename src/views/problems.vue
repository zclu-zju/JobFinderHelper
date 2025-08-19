<script>
import {onMounted, ref, watch} from "vue";
import {useRoute} from "vue-router";
import {search_problem, get_problem_random, create_problem, update_problem_tag} from "@/services/problemsService.js";
import router from "@/router/index.js";
import {ElMessage} from "element-plus";

export default {
  name: "problems",
  setup() {
    const loading = ref(true);

    const question = ref("");
    const source = ref("");

    const kw = ref("");
    const tag = ref("");

    const total = ref(0);
    const pagesize = ref(10);
    const page = ref(1);

    const tags = ref({
      basic: "基础",
      collection: "集合",
      concurrent: "并发",
      network: "网络",
      os: "系统",
      design: "设计",
      sql: "SQL",
      redis: "Redis",
      io: "I/O",
      jvm: "JVM",
      spring: "Spring",
      other: "Other",
    });

    const route = useRoute();
    if (route.query.kw) {
      kw.value = route.query.kw;
    }
    if (route.query.tag) {
      tag.value = route.query.tag;
    }
    if (route.query.pagesize) {
      pagesize.value = parseInt(route.query.pagesize);
    }
    if (route.query.page) {
      page.value = parseInt(route.query.page);
    }
    const problems = ref([]);
    const createNewProblem = async () => {
      try {
        if (!question.value.length) {
          ElMessage.error({message: "题目为空，提交失败"})
          return;
        }
        await create_problem(question.value, source.value);
        ElMessage.success({message: "提交成功"})
      } catch (error) {
        ElMessage.error({message: error})
        console.log(error);
      } finally {
      }
    }
    const fetchRandomProblem = async () => {
      loading.value = true;
      try {
        problems.value = [];
        const problem = await get_problem_random();
        problems.value.push(problem.data);
      } catch (error) {
        console.error(error);
      } finally {
        loading.value = false;
      }
    }
    const fetchProblems = async () => {
      loading.value = true;
      try {
        problems.value = [];
        const response = await search_problem(kw.value, tag.value, page.value, pagesize.value);
        total.value = response.data.total;
        const res_problems = response.data.problems;
        for (const problem of res_problems) {
          problems.value.push(problem);
        }
      } catch (error) {
        console.error(error);
      } finally {
        loading.value = false;
      }
    }
    const searchProblems = () => {
      router.push({
        name: "problems",
        query: {
          kw: kw.value,
          tag: tag.value,
          page: page.value,
          pagesize: pagesize.value,
        }
      });
    }
    const filterProblemsByTag = (tag, status) => {
      router.push({
        name: "problems",
        query: {
          kw: kw.value,
          tag: status ? tag : "",
        }
      });
    }
    const updateTag = async (id, tag) => {
      console.log(id, tag)
      try {
        await update_problem_tag(id, tag);
      } catch (error) {
        console.error(error);
        ElMessage.error({message: error})
      }
    }

    watch(() => route.query.kw, () => fetchProblems, {immediate: true});
    onMounted(() => {
      fetchProblems();
    })
    document.title = route.meta.title;
    return {
      problems,
      createNewProblem,
      fetchRandomProblem,
      fetchProblems,
      updateTag,
      searchProblems,
      filterProblemsByTag,
      router,
      kw,
      loading,
      question,
      source,
      tag,
      tags,

      total,
      pagesize,
      page
    }
  }
}
</script>

<template>
  <el-row justify="center" :gutter="20" v-loading="loading">
    <!--    <el-col :xs="0" :sm="0" :md="8" style="">-->
    <!--      <el-card header="新建题目" shadow="never">-->
    <!--        <el-form label-position="top">-->
    <!--          <el-form-item label="题目">-->
    <!--            <el-input v-model="question"></el-input>-->
    <!--          </el-form-item>-->
    <!--          <el-form-item label="来源">-->
    <!--            <el-input v-model="source"></el-input>-->
    <!--          </el-form-item>-->
    <!--        </el-form>-->
    <!--        <template #footer>-->
    <!--          <el-button type="success" plain style="" @click="createNewProblem">提交</el-button>-->
    <!--        </template>-->
    <!--      </el-card>-->
    <!--    </el-col>-->
    <el-col :xs="24" :sm="16" :md="14">
      <div style="display: flex;align-items:center;justify-content: space-between;">
        <el-button size="large" type="primary" plain @click="fetchRandomProblem">随机题目</el-button>
        <el-scrollbar style="width: 100%;">
          <el-space style="padding: 0 5px">
            <el-check-tag v-for="(value, key) in tags" :checked="key === tag"
                          @change="(val)=>{filterProblemsByTag(key, val)}"
            >{{
                value
              }}
            </el-check-tag>
          </el-space>
        </el-scrollbar>
      </div>
      <el-row>
        <el-col :xs="24" :sm="24" :md="8">
          <el-form @submit="searchProblems" v-loading="loading">
            <el-input size="large" style="margin-top: .5em" v-model="kw"
                      placeholder="Search problems..." clearable
                      @clear="searchProblems"
            ></el-input>
          </el-form>
        </el-col>
        <el-col :xs="0" :sm="0" :md="16">
          <div style="display:flex; align-items: end;justify-content: center;height: 100%;">
            <el-pagination :page-size="pagesize" v-model:current-page="page" :total="total"
                           layout="prev, pager, next"
                           v-loading="loading"
                           @current-change="searchProblems"
            ></el-pagination>
          </div>
        </el-col>
        <el-col :xs="24" :sm="24" :md="0">
          <div style="display:flex; align-items: center;justify-content: center;height: 100%;">
            <el-pagination :page-size="pagesize" v-model:current-page="page" :total="total"
                           layout="prev, pager, next"
                           v-loading="loading"
                           @current-change="searchProblems"
            ></el-pagination>
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="0" :sm="0" :md="24">
          <el-divider border-style="dashed"/>
        </el-col>
      </el-row>
      <el-watermark :content="['zclu@zju.edu.cn']" :gap="[50, 150]">
        <el-scrollbar style="max-height: calc(100vh - 240px); height: calc(100vh - 240px);">
          <el-skeleton :rows="5" animated v-if="loading"/>
          <el-card v-for="problem in problems" :key="problem.id" shadow="hover" style="margin-bottom: 10px;">
            <template #header>
              <el-row :gutter="20">
                <el-col :xs="16" :sm="8" style="display: flex; align-items: center;">
                  <el-text size="large" style="font-weight: bold"># {{ problem.id }}</el-text>
                  <el-tag size="large" v-if="problem.tag.length !== 0" style="margin-left: 1em; ">{{ tags[problem.tag] }}</el-tag>
                </el-col>
                <el-col :xs="24" :sm="16" v-if="problem.tag.length === 0">
                  <el-form style="display: flex; align-items: center; justify-content: space-between;">
                    <el-radio-group v-model="problem.tag" @change="updateTag(problem.id, problem.tag)">
                      <div style="width: 100%">
                        <el-radio value="basic" @click="updateTag(problem.id, 'basic')">基础</el-radio>
                        <el-radio value="collection" @click="updateTag(problem.id, 'collection')">集合</el-radio>
                        <el-radio value="concurrent" @click="updateTag(problem.id, 'concurrent')">并发</el-radio>
                        <el-radio value="network" @click="updateTag(problem.id, 'network')">网络</el-radio>
                        <el-radio value="os" @click="updateTag(problem.id, 'os')">OS</el-radio>
                        <el-radio value="design" @click="updateTag(problem.id, 'design')">设计</el-radio>
                      </div>
                      <div style="width: 100%">
                        <el-radio value="sql" @click="updateTag(problem.id, 'sql')">SQL</el-radio>
                        <el-radio value="redis" @click="updateTag(problem.id, 'redis')">Redis</el-radio>
                        <el-radio value="io" @click="updateTag(problem.id, 'io')">Java IO</el-radio>
                        <el-radio value="jvm" @click="updateTag(problem.id, 'jvm')">JVM</el-radio>
                        <el-radio value="spring" @click="updateTag(problem.id, 'spring')">Spring</el-radio>
                        <el-radio value="other" @click="updateTag(problem.id, 'other')">Other</el-radio>
                      </div>
                    </el-radio-group>
                  </el-form>
                </el-col>
              </el-row>
            </template>
            <template #default>
              <el-text v-html="problem.question"></el-text>
            </template>
            <template #footer>
              <el-link :href="problem.source" target="_blank" :underline="false">{{ problem.source }}</el-link>
            </template>
          </el-card>
          <el-divider>
            <el-text type="info">End</el-text>
          </el-divider>
        </el-scrollbar>
      </el-watermark>
    </el-col>
  </el-row>
</template>

<style scoped>

</style>