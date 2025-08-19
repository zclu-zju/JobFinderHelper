<script>
import {ref, onMounted} from "vue";
import {get_question_random} from "@/services/osJobsService.js";

export default {
  name: "osjobsRandom",
  setup() {
    const question = ref(null);
    const loading = ref(true);
    const errorStatus = ref(false);
    const errorMsg = ref(null);
    const dialogVisible = ref(false);
    const fetchQuestion = async () => {
      loading.value = true;
      try {
        question.value = await get_question_random();
        dialogVisible.value = true;
      } catch (error) {
        errorStatus.value = true;
        errorMsg.value = error;
      } finally {
        loading.value = false;
      }
    }
    onMounted(() => {
      fetchQuestion();
    })
    return {
      question,
      errorStatus,
      errorMsg,
      loading,
      fetchQuestion,
      dialogVisible
    }
  }
}
</script>

<template>
  <el-alert v-if="errorStatus" title="错误" type="error" :description="errorMsg"/>
  <el-row>
    <el-col :xs="0" :sm="4"></el-col>
    <el-col :xs="24" :sm="16">
      <el-descriptions
          title="Random Question"
          direction="vertical"
          :column="1"
          border
          v-loading="loading"
      >
        <el-descriptions-item label="问题"><span v-if="question">{{ question.data.question }}</span></el-descriptions-item>
        <el-descriptions-item label="类型"><span v-if="question">{{ question.data.tag }}</span></el-descriptions-item>
        <el-descriptions-item label="频率"><span v-if="question">{{ question.data.vote }}</span></el-descriptions-item>
      </el-descriptions>
    </el-col>
    <el-col :xs="0" :sm="4"></el-col>
  </el-row>
</template>

<style scoped>

</style>