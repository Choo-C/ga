import request from '@/utils/request'

// 查询有道翻译列表
export function listYoudaoapi(query) {
  return request({
    url: '/business/youdaoapi/list',
    method: 'get',
    params: query
  })
}

// 查询有道翻译详细
export function getYoudaoapi(youdaoKey) {
  return request({
    url: '/business/youdaoapi/' + youdaoKey,
    method: 'get'
  })
}

// 新增有道翻译
export function addYoudaoapi(data) {
  return request({
    url: '/business/youdaoapi',
    method: 'post',
    data: data
  })
}

// 修改有道翻译
export function updateYoudaoapi(data) {
  return request({
    url: '/business/youdaoapi',
    method: 'put',
    data: data
  })
}

// 删除有道翻译
export function delYoudaoapi(youdaoKey) {
  return request({
    url: '/business/youdaoapi/' + youdaoKey,
    method: 'delete'
  })
}
