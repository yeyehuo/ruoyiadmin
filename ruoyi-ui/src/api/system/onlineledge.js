import request from '@/utils/request'

// 查询在线知识库列表
export function listOnlineledge(query) {
  return request({
    url: '/system/onlineledge/list',
    method: 'get',
    params: query
  })
}

// 查询在线知识库详细
export function getOnlineledge(linkId) {
  return request({
    url: '/system/onlineledge/' + linkId,
    method: 'get'
  })
}

// 新增在线知识库
export function addOnlineledge(data) {
  return request({
    url: '/system/onlineledge',
    method: 'post',
    data: data
  })
}

// 修改在线知识库
export function updateOnlineledge(data) {
  return request({
    url: '/system/onlineledge',
    method: 'put',
    data: data
  })
}

// 删除在线知识库
export function delOnlineledge(linkId) {
  return request({
    url: '/system/onlineledge/' + linkId,
    method: 'delete'
  })
}
