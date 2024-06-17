<template>
<div>
    <div id="container"></div>
</div>
</template>

<script>
import {
    Bar
} from '@antv/g2plot';
export default {
    name: "test-page",
    components: {},
    props: {},
    data() {
        return {
            stackedBarPlot: {},
            userData: [
                {
                    title: '已选网课进度',
                    type: '总量',
                    value: 6,
                },
                {
                    title: '已选网课进度',
                    type: '完成量',
                    value: 5,
                },
                {
                    title: '已选网课进度',
                    type: '访问次数',
                    value: 2,
                },
                {
                    title: '已选网课进度',
                    type: '答疑次数',
                    value: 10,
                },
                {
                    title: '作业完成情况',
                    type: '总量',
                    value: 3,
                },
                {
                    title: '作业完成情况',
                    type: '已完成量',
                    value: 3,
                },
                {
                    title: '作业完成情况',
                    type: '撤回量',
                    value: 1,
                },
                {
                    title: '作业完成情况',
                    type: '答疑次数',
                    value: 5,
                },
                {
                    title: '推荐学习表',
                    type: '每日学习量',
                    value: 10,
                },
                {
                    title: '推荐学习表',
                    type: '建议复习量',
                    value: 11,
                },
                {
                    title: '推荐学习表',
                    type: '作业复习量',
                    value: 7,
                },
                {
                    title: '推荐学习表',
                    type: '预期答疑次数',
                    value: 15,
            }]
        }
    },
    methods: {
        init() {
            this.initAntvFx();
        },
        initAntvFx() {
            var data = this.userData;
            this.stackedBarPlot = new Bar('container', {
                data,
                isGroup: true,
                xField: 'value',
                yField: 'title',
                seriesField: 'type',
                dodgePadding: 4,
                label: {
                    position: 'middle', // 'left', 'middle', 'right'
                    layout: [
                        {
                            type: 'interval-adjust-position'
                        },
                        {
                            type: 'interval-hide-overlap'
                        },
                        {
                            type: 'adjust-color'
                        },
                    ],
                },
            });
            this.stackedBarPlot.render();
        },
    },
    mounted() {
        this.init();
    },
};
</script>

<style lang="less" scoped>
#container {
    width: 100%;
    height: 700px;
    margin-top: 20px;
}

.antvTitle {
    font-size: 26px;
    text-align: center;
    justify-content: center;
    display: flex;
}
</style>
