<template>
    <p>
        <a-space>
            <a-button type="primary" @click="handleQuery()">刷新</a-button>
                            <a-button type="primary" @click="onAdd">新增</a-button>
        </a-space>
    </p>
    <a-table :dataSource="appManages"
             :columns="columns"
             :pagination="pagination"
             @change="handleTableChange"
             :loading="loading">
        <template #bodyCell="{ column, record }">
            <template v-if="column.dataIndex === 'operation'">
                    <a-space>
                        <a-popconfirm
                                title="删除后不可恢复，确认删除?"
                                @confirm="onDelete(record)"
                                ok-text="确认" cancel-text="取消">
                            <a style="color: red">删除</a>
                        </a-popconfirm>
                        <a @click="onEdit(record)">编辑</a>
                    </a-space>
            </template>
        </template>
    </a-table>
        <a-modal v-model:visible="visible" title="" @ok="handleOk"
                 ok-text="确认" cancel-text="取消">
            <a-form :model="appManage" :label-col="{span: 4}" :wrapper-col="{ span: 20 }">
                        <a-form-item label="版本号">
                                <a-input v-model:value="appManage.appVersion"/>
                        </a-form-item>
                        <a-form-item label="APP名">
                                <a-input v-model:value="appManage.appName"/>
                        </a-form-item>
                        <a-form-item label="平台">
                                <a-input v-model:value="appManage.platform"/>
                        </a-form-item>
                        <a-form-item label="下载地址">
                                <a-input v-model:value="appManage.downloadUrl"/>
                        </a-form-item>
                        <a-form-item label="描述">
                                <a-input v-model:value="appManage.description"/>
                        </a-form-item>
            </a-form>
        </a-modal>
</template>

<script setup>
    import {ref, onMounted} from 'vue';
    import {notification} from "ant-design-vue";
    import axios from "axios";

    const visible = ref(false);
    let appManage = ref({
        id: undefined,
        appVersion: undefined,
        appName: undefined,
        platform: undefined,
        downloadUrl: undefined,
        description: undefined,
        createTime: undefined,
    });
    const appManages = ref([]);
    // 分页的三个属性名是固定的
    const pagination = ref({
        total: 0,
        current: 1,
        pageSize: 10,
    });
    let loading = ref(false);
    const columns = [
        {
            title: '版本号',
            dataIndex: 'appVersion',
            key: 'appVersion',
        },
        {
            title: 'APP名',
            dataIndex: 'appName',
            key: 'appName',
        },
        {
            title: '平台',
            dataIndex: 'platform',
            key: 'platform',
        },
        {
            title: '下载地址',
            dataIndex: 'downloadUrl',
            key: 'downloadUrl',
        },
        {
            title: '描述',
            dataIndex: 'description',
            key: 'description',
        },
        {
            title: '操作',
            dataIndex: 'operation'
        }
    ];

    const onAdd = () => {
        appManage.value = {};
        visible.value = true;
    };

    const onEdit = (record) => {
        appManage.value = window.Tool.copy(record);
        visible.value = true;
    };

    const onDelete = (record) => {
        axios.delete("/business/admin/app-manage/delete/" + record.id).then((data) => {
            if (data.success) {
                notification.success({description: "删除成功！"});
                handleQuery({
                    page: pagination.value.current,
                    size: pagination.value.pageSize,
                });
            } else {
                notification.error({description: data.message});
            }
        });
    };

    const handleOk = () => {
        axios.post("/business/admin/app-manage/save", appManage.value).then((data) => {
            if (data.success) {
                notification.success({description: "保存成功！"});
                visible.value = false;
                handleQuery({
                    page: pagination.value.current,
                    size: pagination.value.pageSize
                });
            } else {
                notification.error({description: data.message});
            }
        });
    };

    const handleQuery = (param) => {
        if (!param) {
            param = {
                page: 1,
                size: pagination.value.pageSize
            };
        }
        loading.value = true;
        axios.get("/business/admin/app-manage/query-list", {
            params: {
                page: param.page,
                size: param.size
            }
        }).then((data) => {
            loading.value = false;
            if (data.success) {
                appManages.value = data.data.list;
                // 设置分页控件的值
                pagination.value.current = param.page;
                pagination.value.total = data.data.total;
            } else {
                notification.error({description: data.message});
            }
        });
    };

    const handleTableChange = (pagination) => {
        handleQuery({
            page: pagination.current,
            size: pagination.pageSize
        });
    };

    onMounted(() => {
        handleQuery({
            page: 1,
            size: pagination.value.pageSize
        });
    });
</script>