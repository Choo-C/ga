import request from '@/utils/request'

// 查询演示列表
export function listDemo(query) {
  return request({
    url: '/business/demo/list',
    method: 'get',
    params: query
  })
}

// 查询演示详细
export function getDemo(id) {
  return request({
    url: '/business/demo/' + id,
    method: 'get'
  })
}

// 新增演示
export function addDemo(data) {
  return request({
    url: '/business/demo',
    method: 'post',
    data: data
  })
}

// 修改演示
export function updateDemo(data) {
  return request({
    url: '/business/demo',
    method: 'put',
    data: data
  })
}

// 删除演示
export function delDemo(id) {
  return request({
    url: '/business/demo/' + id,
    method: 'delete'
  })
}
export function qwe() {
  console.error("asdqwe")
  return null;
}
