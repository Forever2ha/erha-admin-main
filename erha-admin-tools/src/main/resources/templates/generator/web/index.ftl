<template>
  <div class="container">
    <div class="panel">
      <div style="position: relative; height: 100%">
        <!--查询表单-->
<#if (countQueryType < 3)>
        <a-row :gutter="24" style="margin-bottom: 12px">
            <#if (countQueryType == 0)>
          <!--没有查询字段，字段生成主键的查询方式-->
          <a-col :span="6">
            <!--id搜索框-->
            <a-input-number
              v-model="crud.options.query.${pkChangeColName}"
              placeholder="输入id搜索"
            />
          </a-col>
            <#else >
                <#list columns as col>
                    <#if (col.queryType?length > 0)!false>
          <!--${col.remark}搜索框-->
          <a-col :span="6">
                        <#if col.columnType == 'Timestamp' >
                          <#if col.queryType == 'Between'>
            <a-range-picker
              v-model="pickerValue${col.capitalColumnName}"
              :placeholder="['[${col.remark}', '${col.remark}]']"
              @change="rangePicker${col.capitalColumnName}Change"
            />
                            <#else >
            <a-date-picker
              v-model="crud.options.query.${col.changeColumnName}"
              placeholder="检索方式: ${col.queryType}"
              show-time
              format="YYYY-MM-DD HH:mm:ss"
              style="width: 100%"
            />
                        </#if>
                        <#elseif col.formType == '文本框' || col.formType == '文本域' || col.formType == '未设置'>
                          <#if col.queryType == 'Between'>
            <a-input-group>
              <a-input v-model="crud.options.query.start${col.capitalColumnName}" placeholder="${col.remark}">
                <template #prefix>[</template>
                <template #suffix>,</template>
              </a-input>
              <a-input v-model="crud.options.query.end${col.capitalColumnName}" placeholder="${col.remark}">
                <template #suffix>]</template>
              </a-input>
            </a-input-group>
                            <#else >
            <a-input<#if col.columnType != 'Boolean' && col.columnType != 'String' && col.columnType != 'Timestamp'>-number</#if>
                    v-model="crud.options.query.${col.changeColumnName}"
                    placeholder="输入${col.remark}搜索"
            >
              <template #prefix> ${col.queryType} </template>
            </a-input<#if col.columnType != 'Boolean' && col.columnType != 'String' && col.columnType != 'Timestamp'>-number</#if>>
                          </#if>
                        <#else>
            <a-select
              v-model="crud.options.query.${col.changeColumnName}"
              placeholder="输入${col.remark}搜索"
              <#if col.formType == '下拉框[多选]' >
              multiple</#if>
            >
              <a-option
                v-for="s in dict.${col.dictName}"
                :key="s.detailId"
                :value="s.<#if col.columnType == 'Boolean'>value<#else >label</#if>"
              >
                {{ s.label }}
              </a-option>
            </a-select>
                        </#if>
          </a-col>

                    </#if>
                </#list>
            </#if>
          <a-col :span="6">
            <RROperation />
          </a-col>
        </a-row>
<#else >
        <a-row>
          <a-col :flex="1">
            <a-form
              :model="crud.options.query"
              :label-col-props="{ span: 6 }"
              :wrapper-col-props="{ span: 18 }"
              label-align="left"
            >
              <a-scrollbar style="height: 104px; overflow: auto">
                <a-row :gutter="16" style="width: 100%">
                    <#list columns as col>
                        <#if (col.queryType?length > 0)!false>
                  <!--${col.remark}搜索框-->
                  <a-col :span="8">
                    <a-form-item field="${col.changeColumnName}" label="${col.remark}">
                        <#if col.columnType == 'Timestamp'>
                            <#if col.queryType == 'Between'>
                      <a-range-picker
                        v-model="pickerValue${col.capitalColumnName}"
                        :placeholder="['[${col.remark}', '${col.remark}]']"
                        @change="rangePicker${col.capitalColumnName}Change"
                      />
                            <#else >
                      <a-date-picker
                        v-model="crud.options.query.${col.changeColumnName}"
                        placeholder="检索方式: ${col.queryType}"
                        show-time
                        format="YYYY-MM-DD HH:mm:ss"
                        style="width: 100%"
                      />
                            </#if>
                        <#elseif col.formType == '文本框' || col.formType == '文本域' || col.formType == '未设置'>
                            <#if col.queryType == 'Between'>
                      <a-input-group>
                        <a-input v-model="crud.options.query.start${col.capitalColumnName}" placeholder="${col.remark}">
                          <template #prefix>[</template>
                          <template #suffix>,</template>
                        </a-input>
                        <a-input v-model="crud.options.query.end${col.capitalColumnName}" placeholder="${col.remark}">
                          <template #suffix>]</template>
                        </a-input>
                      </a-input-group>
                            <#else >
                      <a-input<#if col.columnType != 'Boolean' && col.columnType != 'String' && col.columnType != 'Timestamp'>-number</#if>
                        v-model="crud.options.query.${col.changeColumnName}"
                        placeholder="输入${col.remark}搜索"
                      >
                        <template #prefix> ${col.queryType} </template>
                      </a-input<#if col.columnType != 'Boolean' && col.columnType != 'String' && col.columnType != 'Timestamp'>-number</#if>>
                            </#if>
                        <#else  >
                      <a-select
                        v-model="crud.options.query.${col.changeColumnName}"
                        placeholder="输入${col.remark}搜索"
                        <#if col.formType == '下拉框[多选]' >
                        multiple</#if>
                      >
                        <a-option
                          v-for="s in dict.${col.dictName}"
                          :key="s.detailId"
                          :value="s.<#if col.columnType == 'Boolean'>value<#else >label</#if>"
                        >
                          {{ s.label }}
                        </a-option>
                      </a-select>
                        </#if>
                    </a-form-item>
                  </a-col>
                        </#if>
                    </#list>
                </a-row>
              </a-scrollbar>
            </a-form>
          </a-col>
          <a-divider style="height: 84px" direction="vertical" />
          <a-col :flex="'86px'" style="text-align: right">
            <a-space size="medium" direction="vertical">
              <a-button
                status="success"
                long
                :disabled="crud.options.tableInfo.isEdit"
                @click="crud.method.refresh"
              >
                <template #icon> <icon-search /> </template
                >{{ $t('crud.search') }}</a-button
              ><a-button
                status="warning"
                :disabled="crud.options.tableInfo.isEdit"
                @click="crud.update.resetParams"
              >
                <template #icon> <icon-refresh /> </template>
                {{ $t('crud.reset') }}
              </a-button>
            </a-space>
          </a-col>
        </a-row>
        <a-divider style="margin-top: 0" />
</#if>
        <CrudOperation
          :add-permission="['${changeClassName}:list']"
          :edit-permission="['${changeClassName}:edit']"
          :del-permission="['${changeClassName}:del']"
          :download-permission="['${changeClassName}:list']"
          style="margin-bottom: 12px"
        >
          <template #addForm>
            <a-row :gutter="12">
<#list columns as col>
  <#if col.formShow && col.formType != '文本域'>
              <!--${col.remark}-->
              <a-col :span="12">
                <a-form-item
                  field="${col.changeColumnName}"
                  label="${col.remark}"
                  <#if col.istNotNull>
                  :rules="[{ required: true, message: '${col.remark}不能为空' }]"
                  </#if>
                >
                    <#if col.formType == '文本框'>
                  <a-input<#if col.columnType != 'Boolean' && col.columnType != 'String' && col.columnType != 'Timestamp'>-number</#if> v-model="crud.options.form.${col.changeColumnName}" />
                    <#elseif col.formType == '开关[仅两个值]'>
                  <a-radio-group v-model="crud.options.form.${col.changeColumnName}">
                    <a-radio
                      v-for="s in dict.${col.dictName}"
                      :key="s.detailId"
                      :value="s.value"
                      >{{ s.label }}</a-radio
                    >
                  </a-radio-group>
                    <#elseif col.formType == '单选框[圆圈]'>
                  <a-radio-group v-model="crud.options.form.${col.changeColumnName}">
                    <a-radio
                      v-for="s in dict.${col.dictName}"
                      :key="s.detailId"
                      :value="s.<#if col.columnType == 'Boolean'>value<#else >label</#if>"
                      >{{ s.label }}</a-radio
                    >
                  </a-radio-group>
                    <#elseif col.formType == '单选框[按钮]'>
                  <a-radio-group
                    v-model="crud.options.form.${col.changeColumnName}"
                    type="button"
                  >
                    <a-radio
                      v-for="s in dict.${col.dictName}"
                      :key="s.detailId"
                      :value="s.<#if col.columnType == 'Boolean'>value<#else >label</#if>"
                      >{{ s.label }}</a-radio
                    >
                  </a-radio-group>
                    <#elseif col.formType == '下拉框[单选]'>
                  <a-select
                    v-model="crud.options.form.${col.changeColumnName}"
                    placeholder="请选择"
                  >
                    <a-option
                      v-for="s in dict.${col.dictName}"
                      :key="s.detailId"
                      :value="s.<#if col.columnType == 'Boolean'>value<#else >label</#if>"
                      >{{ s.label }}</a-option
                    >
                  </a-select>
                    <#elseif col.formType == '下拉框[多选]'>
                  <a-select v-model="crud.options.form.${col.changeColumnName}" placeholder="请选择" multiple>
                    <a-option
                      v-for="s in dict.${col.dictName}"
                      :key="s.detailId"
                      :value="s.label"
                      >{{ s.label }}</a-option
                    >
                  </a-select>
                    <#elseif col.formType == '日期框'>
                  <a-date-picker
                    v-model="crud.options.form.${col.changeColumnName}"
                    show-time
                    format="YYYY-MM-DD HH:mm:ss"
                    style="width: 100%"
                  />
                    </#if>
                </a-form-item>
              </a-col>
  </#if>
</#list>
<#list columns as col>
  <#if col.formShow && col.formType == '文本域'>
              <!--${col.remark}-->
              <a-col :span="24">
                <a-form-item
                  field="${col.changeColumnName}"
                  label="${col.remark}"
                  <#if col.istNotNull>
                    :rules="[
                    { required: true, message: '${col.remark}不能为空' }
                    ]"
                  </#if>
                >
                  <a-textarea v-model="crud.options.form.${col.changeColumnName}"/>
                </a-form-item>
              </a-col>
  </#if>
</#list>
            </a-row>
          </template>
        </CrudOperation>

        <!--表格-->
        <a-table
          ref="table"
          v-model:selectedKeys="crud.options.tableInfo.selectKeys"
          row-key="id"
          :columns="tableColumns"
          :data="crud.options.tableInfo.data"
          :pagination="false"
          :scroll="{ y: '100%' }"
          :loading="crud.status.value === CrudStatus.REFRESHING"
          :column-resizable="
            crud.options.tableInfo.componentConfig.colResizable
          "
          :bordered="{
            cell: crud.options.tableInfo.componentConfig.border,
          }"
          :stripe="crud.options.tableInfo.componentConfig.stripe"
          :show-header="crud.options.tableInfo.componentConfig.tableHeader"
          :row-selection="
            crud.options.tableInfo.componentConfig.checkbox
              ? {
                  type: 'checkbox',
                  showCheckedAll: true,
                }
              : undefined
          "
          style="height: calc(100% - <#if (countQueryType >= 3) >209<#else >128</#if>px); margin-bottom: 12px"
        >
          <!--修改结果-->
          <template #result="{ record }">
            <!--修改完毕并且不完全修改成功的时候展示-->
            <div
              v-show="
                crud.options.tableInfo.selectKeys.includes(record.id) &&
                crud.options.tableInfo.isEdit
              "
            >
              <!--修改成功的行-->
              <div
                v-show="
                  !record.updateErr && typeof record.updateErr === 'boolean'
                "
              >
                <a-popover>
                  <a-tag color="green">
                    <icon-check />
                  </a-tag>

                  <template #title> 更改成功 </template>
                </a-popover>
              </div>
              <!--修改失败的行-->
              <div v-show="record.updateErr !== false">
                <div v-show="record.updateErr === undefined">
                  <a-tag color="blue"> <icon-edit />... </a-tag>
                </div>
                <div v-show="record.updateErr">
                  <a-popover>
                    <a-tag color="red">
                      <icon-close />
                    </a-tag>
                    <template #title> 更改失败 </template>
                    <template #content>
                      <a-list size="small">
                        <a-list-item
                          v-for="(err, index) in record.updateErr"
                          :key="index"
                        >
                          [{{ err.errorField }}]
                          {{ err.errorMsg }}
                          --->[{{ err.errorVal }}]
                        </a-list-item>
                      </a-list>
                    </template>
                  </a-popover>
                </div>
              </div>
            </div>
          </template>

<#list columns as col>
  <#if col.columnShow && col.formShow>
          <!--${col.remark}-->
    <#if col.columnType == 'Timestamp'>
          <template #${col.changeColumnName}="{ record }">
            <!--正常情况下-->
            <div v-show="!record.editable && !crud.options.tableInfo.isEdit">
              {{ record.${col.changeColumnName} }}
            </div>

            <!--修改完毕提交后/未修改的行(若修改全部成功则不会显示)-->
            <div v-if="!record.editable && crud.options.tableInfo.isEdit">
              <!--未修改的行-->
              <div v-show="!crud.options.form[record.id]">
                {{ record.${col.changeColumnName} }}
              </div>
              <!--修改完毕提交后-->
              <div v-if="crud.options.form[record.id]">
                {{
                  crud.options.form[record.id].${col.changeColumnName}
                    ? crud.options.form[record.id].${col.changeColumnName}
                    : record.${col.changeColumnName}
                }}
              </div>
            </div>

            <!--修改情况下-->
            <div v-if="record.editable">
              <a-date-picker
                v-model="crud.options.form[record.id].${col.changeColumnName}"
                show-time
                :default-value="record.${col.changeColumnName}"
                format="YYYY-MM-DD HH:mm:ss"
                style="width: 100%"
              />
            </div>
          </template>
    <#elseif col.formType == '开关[仅两个值]' >
          <template #${col.changeColumnName}="{ record }">
            <div>
              <a-switch
                v-model="record.${col.changeColumnName}"
                :loading="${col.changeColumnName}.loading.value"
                @change="enable${col.capitalColumnName}Change(record)"
              >
                <template #checked>{{
                  dict.${col.dictName}
                    ? dict.${col.dictName}.filter(
                        (di) => di.value === '1' || di.value === 'true'
                    )[0].label
                    : ''
                }}</template>
                <template #unchecked>{{
                  dict.${col.dictName}
                    ? dict.${col.dictName}.filter(
                        (di) => di.value === '0' || di.value === 'false'
                      )[0].label
                    : ''
                }}</template>
              </a-switch>
            </div>
          </template>
    <#elseif col.formType == '下拉框[单选]' || col.formType == '单选框[圆圈]' || col.formType == '单选框[按钮]'>
          <template #${col.changeColumnName}="{ record }">
            <!--正常情况下-->
            <div v-show="!record.editable && !crud.options.tableInfo.isEdit">
            <#if col.columnType == 'Boolean'>
              {{ dict.${col.dictName} ? (dict.${col.dictName}.filter((di: any) => di.value === (record.${col.changeColumnName} ? '1' : '0') || di.value === (record.${col.changeColumnName} + '')))[0].label : ''}}
            <#else >
              {{ record.${col.changeColumnName} }}
            </#if>
            </div>

            <!--修改完毕提交后/未修改的行(若修改全部成功则不会显示)-->
            <div v-if="!record.editable && crud.options.tableInfo.isEdit">
              <!--未修改的行-->
              <div v-show="!crud.options.form[record.id]">
                <#if col.columnType == 'Boolean'>
                {{ dict.${col.dictName} ? (dict.${col.dictName}.filter((di: any) => di.value === (record.${col.changeColumnName} ? '1' : '0') || di.value === (record.${col.changeColumnName} + '')))[0].label : '' }}
                <#else >
                {{ record.${col.changeColumnName} }}
                </#if>
              </div>
              <!--修改完毕提交后-->
              <div v-if="crud.options.form[record.id]">
              <#if col.columnType == 'Boolean'>
                {{
                  crud.options.form[record.id].${col.changeColumnName}
                   ? dict.${col.dictName} ? (dict.${col.dictName}.filter((di: any) => di.value === (crud.options.form[record.id].${col.changeColumnName} ? '1' : '0') || di.value === (record.${col.changeColumnName} + '')))[0].label : ''
                   : dict.${col.dictName} ? (dict.${col.dictName}.filter((di: any) => di.value === (record.${col.changeColumnName} ? '1' : '0') || di.value === (record.${col.changeColumnName} + '')))[0].label : ''
                }}
              <#else >
                {{
                  crud.options.form[record.id].${col.changeColumnName}
                    ? crud.options.form[record.id].${col.changeColumnName}
                    : record.${col.changeColumnName}
                }}
              </#if>
              </div>
            </div>

            <!--修改情况下-->
            <div v-if="record.editable">
              <#if col.formShow>
                <#if col.columnType == 'Boolean'>
              <a-select
                v-model="crud.options.form[record.id].${col.changeColumnName}"
                :default-value="record.${col.changeColumnName} ? '1' : '0'"
              >
                <a-option
                  v-for="s in dict.${col.dictName}.map((di) => {
                    if (di.value === 'true') di.value = '1';
                    if (di.value === 'false') di.value = '0';
                    return di;
                  })"
                  :key="s.detailId"
                  :value="s.value"
                  >{{ s.label }}
                </a-option>
              </a-select>
                    <#else >
              <a-select
                v-model="crud.options.form[record.id].${col.changeColumnName}"
                :default-value="record.${col.changeColumnName}"
              >
                <a-option
                  v-for="s in dict.${col.dictName}"
                  :key="s.detailId"
                  :value="s.label"
                  >{{ s.label }}
                </a-option>
              </a-select>
                </#if>
               <#else >
               {{ record.${col.changeColumnName} }}
              </#if>
            </div>
          </template>
    <#elseif col.formType == '下拉框[多选]'>
          <template #${col.changeColumnName}="{ record }">
            <!--正常情况下-->
            <div v-show="!record.editable && !crud.options.tableInfo.isEdit">
              <a-space v-show="!crud.options.form[record.id]">
                <a-tag
                    v-for="(d,index) in record.${col.changeColumnName}"
                        :key="index"
                        bordered
                >
                  {{ d }}
                </a-tag>
              </a-space>
            </div>

            <!--修改完毕提交后/未修改的行(若修改全部成功则不会显示)-->
            <div v-if="!record.editable && crud.options.tableInfo.isEdit">
              <!--未修改的行-->
              <a-space v-show="!crud.options.form[record.id]">
                <a-tag
                  v-for="(d,index) in record.${col.changeColumnName}"
                  :key="index"
                  bordered
                >
                  {{ d }}
                </a-tag>
              </a-space>
              <!--修改完毕提交后-->
              <div v-if="crud.options.form[record.id]">
                <a-select
                  v-model="crud.options.form[record.id].${col.changeColumnName}"
                  :default-value="record.${col.changeColumnName}"
                  multiple
                  :disabled="true"
                >
                  <a-option
                    v-for="(di,index) in dict.${col.dictName}"
                    :key="index"
                    :value="di.label"
                  >{{ di.label }}
                  </a-option>
                </a-select>
              </div>
            </div>

            <!--修改情况下-->
            <div v-if="record.editable">
               <#if col.formShow>
              <a-select
                v-model="crud.options.form[record.id].${col.changeColumnName}"
                :default-value="record.${col.changeColumnName}"
                multiple
              >
                <a-option
                  v-for="(di,index) in dict.${col.dictName}"
                  :key="index"
                  :value="di.label"
                  >{{ di.label }}
               </a-option>
              </a-select>
                   <#else >
               </#if>
            </div>
      </template>
    <#elseif col.formType == '文本框' || col.formType == '文本域' || col.formType == '未设置'>
          <template #<#if col.columnKey == 'PRI'>id<#else >${col.changeColumnName}</#if>="{ record }">
            <!--正常情况下-->
            <div v-show="!record.editable && !crud.options.tableInfo.isEdit">
              <#if col.columnKey == 'PRI'>
              {{ record.id }}
              <#else >
              {{ record.${col.changeColumnName} }}
              </#if>
            </div>

            <!--修改完毕提交后/未修改的行(若修改全部成功则不会显示)-->
            <div v-if="!record.editable && crud.options.tableInfo.isEdit">
              <!--未修改的行-->
              <div v-show="!crud.options.form[record.id]">
                <#if col.columnKey == 'PRI'>
                {{ record.id }}
                <#else >
                {{ record.${col.changeColumnName} }}
                </#if>
              </div>
              <!--修改完毕提交后-->
              <div v-if="crud.options.form[record.id]">
                {{
                  crud.options.form[record.id].<#if col.columnKey == 'PRI'>id<#else>${col.changeColumnName}</#if>
                    ? crud.options.form[record.id].<#if col.columnKey == 'PRI'>id<#else>${col.changeColumnName}</#if>
                    : record.<#if col.columnKey == 'PRI'>id<#else>${col.changeColumnName}</#if>
                }}
              </div>
            </div>

            <!--修改情况下-->
            <div v-if="record.editable">
                <#if col.formShow>
              <a-input<#if col.columnType != 'Boolean' && col.columnType != 'String' && col.columnType != 'Timestamp'>-number</#if>
                v-model="crud.options.form[record.id].<#if col.columnKey == 'PRI'>id<#else>${col.changeColumnName}</#if>"
                :default-value="record.<#if col.columnKey == 'PRI'>id<#else>${col.changeColumnName}</#if>"
              />
                <#else >
              {{ record.<#if col.columnKey == 'PRI'>id<#else>${col.changeColumnName}</#if> }}
                </#if>
            </div>
          </template>
    </#if>
      <#sep >

  </#if>
</#list>
        </a-table>
        <Pagination
          style="position: absolute; right: 0; bottom: 0; padding-right: 7px"
        />
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
  import { useCrud, CrudStatus } from '@/components/crud/CRUD';
  import { ${className} } from '@/api/${path}';
  import { computed, getCurrentInstance, onMounted, provide, ref } from 'vue';
<#if hasDict>
  import { useDict } from '@/components/dict';
  </#if>
  import CrudOperation from '@/components/crud/CrudOperation.vue';
  import RROperation from '@/components/crud/RROperation.vue'
  import Pagination from '@/components/crud/Pagination.vue';
  import axios from 'axios';
<#if hasSwitch>
  import useLoading from '@/hooks/loading';
  </#if>
  import { useI18n } from 'vue-i18n';

  const { t } = useI18n();
  const crud = useCrud<${className}>({
    tag: '${apiAliasShort}',
    url: '/${api_path}',
    title: '${apiShort}',
    tableInfo: {
      componentConfig: {
        stripe: false,
      },
    },
  });
  provide('crud', crud);

<#list columns as col>
  <#if col.formType == '下拉框[多选]'>
  const parseToArray = (str: string): string[] => {
    const s = str.replace('[', '').replace(']', '').split(/[,，] /);
    const res: string[] = [];
    for (let i = 0; i < s.length; i += 1) {
      res.push(s[i]);
    }
    return res;
  };
  <#break>
  </#if>
</#list>


<#if hasDict>
  // 字典
  const dict = useDict(<#list dicts as d>'${d}'<#sep >, </#list>);
  </#if>
  const instance = getCurrentInstance();
  const global = (instance as any).appContext.config.globalProperties;

  // 设置${apiAliasShort} columns信息
  crud.update.setTableColumns([
    {
      title: t('crud.table.update.result'),
      dataIndex: 'result',
      width: 90,
      display: false,
      fixed: 'left',
      slotName: 'result',
      ignoreSwitch: true,
    },
<#list columns as col>
      <#if col.columnShow>
    {
      title: '${col.remark}',
      dataIndex: '<#if col.columnKey == 'PRI'>id<#else >${col.changeColumnName}</#if>',
      width: <#if col.columnType == 'Timestamp'>180<#elseif (col.formType =='开关[仅两个值]')!false>100<#else >150</#if>,
      display: true,
      slotName: '<#if col.columnKey == 'PRI'>id<#else >${col.changeColumnName}</#if>',
      tooltip: true,
      ellipsis: true,
    },
      </#if>
</#list>
  ]);
  const tableColumns = computed(() => {
    return crud.options.tableInfo.columns?.filter((val) => val.display);
  });

  // region    ↓-------------------------------- switch --------------------------------↓
  <#list columns as col>
  <#if col.formShow && col.formType == '开关[仅两个值]'>
  // 状态loading
  const ${col.changeColumnName} = useLoading();
  // ${col.remark}状态改变
  const enable${col.capitalColumnName}Change = async (record: any) => {
    ${col.changeColumnName}.toggle();
    const data = (await axios.put(crud.options.url, [
      { id: record.id, ${col.changeColumnName}: record.${col.changeColumnName} },
    ])) as any;
    if (data.code === 20000) {
      global.$notification.success('更改成功');
    } else {
      global.$notification.warning(`更改失败:${r'${data.msg}'}`);
      record.${col.changeColumnName} = !record.${col.changeColumnName};
    }
    ${col.changeColumnName}.toggle();
  };

  </#if>
  </#list>
  // endregion ↑-------------------------------- switch --------------------------------↑

  // region    ↓-------------------------------- rangePicker --------------------------------↓
<#list betweens as col>
  <#if col.columnType == 'Timestamp' && col.queryType??>
  // rangePicker${col.capitalColumnName}的值改变
  function rangePicker${col.capitalColumnName}Change(data: any) {
      crud.update.appendQueryParams({
          start${col.capitalColumnName}: `${r'${data[0]}'} 00:00:00`,
          end${col.capitalColumnName}: `${r'${data[1]}'} 00:00:00`,
      });
  }
  // 解决重置params rangerPicker${col.capitalColumnName}不清除数据的问题
  const pickerValue${col.capitalColumnName} = ref([]);
  </#if>

</#list>
  // endregion ↑-------------------------------- rangePicker --------------------------------↑

  // region    ↓-------------------------------- 钩子 --------------------------------↓
  onMounted(() => {
    crud.method.refresh();
  });
<#list betweens as col>
  <#if col.columnType == 'Timestamp' && col.queryType??>
  crud.hook.afterResetParams = () => {
      <#list betweens as col>
      <#if col.columnType == 'Timestamp'>
    pickerValue${col.capitalColumnName}.value = [];
      </#if>
      </#list>
  };
  </#if>
  <#break >
</#list>

<#list columns as col>
  <#if col.formType == '下拉框[多选]' && col.queryType??>
  crud.hook.afterRefresh = () => {
    for (let i = 0; i < (crud.options.tableInfo as any).data.length; i += 1) {
          <#list columns as col>
            <#if col.formType == '下拉框[多选]' && col.queryType??>
      (crud.options.tableInfo as any).data[i].${col.changeColumnName} = parseToArray((crud.options.tableInfo as any).data[i].${col.changeColumnName});
            </#if>
          </#list>
    }
      <#list columns as col>
        <#if col.formType == '下拉框[多选]' && col.queryType??>
    if (crud.options.query.${col.changeColumnName}) {
      crud.options.query.${col.changeColumnName} = parseToArray(crud.options.query.${col.changeColumnName});
    }
        </#if>
      </#list>

  };
  <#break>
  </#if>
</#list>

<#list columns as col>
  <#if col.formType == '下拉框[多选]' && col.queryType??>
  crud.hook.beforeRefresh = () => {
    <#list columns as col>
    <#if col.formType == '下拉框[多选]' && col.queryType??>
    if (crud.options.query.${col.changeColumnName}){
      let newStr = '';
      for (let i = 0; i < crud.options.query.${col.changeColumnName}.length; i += 1) {
        newStr += crud.options.query.${col.changeColumnName}[i];
        if (i !== crud.options.query.${col.changeColumnName}.length - 1) newStr += ', ';
      }
      crud.options.query.${col.changeColumnName} = newStr;
    }
    </#if>
    </#list>
    return true;
  };
  <#break >
  </#if>
</#list>
  // endregion ↑-------------------------------- 钩子 --------------------------------↑
</script>

<style scoped>
  .container {
    height: 100%;
    padding: 16px 20px;
    padding-bottom: 0;
    background-color: var(--color-fill-2);
  }

  .panel {
    height: 100%;
    padding: 16px;
    background-color: var(--color-bg-2);
    border-radius: 4px;
  }
</style>