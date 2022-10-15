package com.lovish.androidquestions.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(var question: String = "", var answer: String = "") : Parcelable