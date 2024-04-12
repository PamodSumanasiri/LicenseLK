package com.basics.licenselk.data.local

import com.basics.licenselk.R
import com.basics.licenselk.data.Tip

object TipList {
    val tipList  = listOf<Tip>(
        Tip(image = R.drawable.img1, tipNo = R.string.tip1, subject = R.string.tip1_subject, body = R.string.tip1_body),
        Tip(image = R.drawable.img2, tipNo = R.string.tip2, subject = R.string.tip2_subject, body = R.string.tip2_body),
        Tip(image = R.drawable.img3, tipNo = R.string.tip3, subject = R.string.tip3_subject, body = R.string.tip3_body),
        Tip(image = R.drawable.img4, tipNo = R.string.tip4, subject = R.string.tip4_subject, body = R.string.tip4_body),
        Tip(image = R.drawable.img5, tipNo = R.string.tip5, subject = R.string.tip5_subject, body = R.string.tip5_body),
        Tip(image = R.drawable.img6, tipNo = R.string.tip6, subject = R.string.tip6_subject, body = R.string.tip6_body),
        Tip(image = R.drawable.img7, tipNo = R.string.tip7, subject = R.string.tip7_subject, body = R.string.tip7_body),
        Tip(image = R.drawable.img8, tipNo = R.string.tip8, subject = R.string.tip8_subject, body = R.string.tip8_body),
        Tip(image = R.drawable.img9, tipNo = R.string.tip9, subject = R.string.tip9_subject, body = R.string.tip9_body),
        Tip(image = R.drawable.img10, tipNo = R.string.tip10, subject = R.string.tip10_subject, body = R.string.tip10_body),

        )
    val default_tip = Tip(-1,-1,-1,-1)
}