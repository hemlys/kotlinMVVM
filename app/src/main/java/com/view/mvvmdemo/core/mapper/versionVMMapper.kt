package com.view.mvvmdemo.core.mapper

import com.view.mvvmdemo.bean.versionBean

class versionVMMapper : Mapper<versionBean, versionBean> {
    override fun map(origin: versionBean) =
        versionBean(
            code = origin.code,
            key = origin.key,
            msg = origin.msg,
            token = origin.token,
            version = origin.version,
            env = origin.env,
            service = origin.service

        )
}
