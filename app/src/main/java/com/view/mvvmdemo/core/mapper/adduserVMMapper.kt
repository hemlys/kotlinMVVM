package com.view.mvvmdemo.core.mapper

import com.view.mvvmdemo.bean.addUserBean

class adduserVMMapper : Mapper<addUserBean, addUserBean> {
    override fun map(origin: addUserBean) =
        addUserBean(
            code = origin.code,
            data = origin.data,
            msg = origin.msg
        )
}