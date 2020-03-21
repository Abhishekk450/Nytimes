package com.example.nytimes.utils

import android.app.Activity
import android.view.View
import com.example.nytimes.model.ErrorHandler
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.ErrorManager

class Utils {

     companion object {
         fun checkIsCurrentDate(givenDate: String): String {
             val initalPattern = "yyyy-MM-dd'T'HH:mm:ss"
             val requiredPattern = "yyyy-MM-dd"
             var outputDate = ""

             val dateFormat = SimpleDateFormat(initalPattern)
             var date: Date? = null
             try {
                 date = dateFormat.parse(givenDate)
                 val simpleDateFormat = SimpleDateFormat(requiredPattern)
                 outputDate = simpleDateFormat.format(date)
                 return outputDate
             } catch (e: Exception) {
                 e.printStackTrace()
             }

             return outputDate
         }

         fun showError(
             activity: Activity?,
             view: View?,
             error: Any?
         ) {
             if (activity == null) return
             val errorManager = ErrorHandler(activity, view!!, error)
             errorManager.handleErrorResponse()
         }
     }
 }