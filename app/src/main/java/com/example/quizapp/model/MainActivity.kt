package com.example.quizapp.model

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val question = arrayOf(
        "What is my name?",
        "From which Institution, I did my Bachelor's degree?",
        "How many months did my internship as an Android App Developer at Personal Sported ?",
        "Which Projects I have worked on?",
        "Which of the following is one of my achievements related to hackathons?",
        "Which app did I develop that focuses on unscrambling words?",
        "Which of the following project is Live on Play Store?",
        "What technology did I use for backend infrastructure development in my Android App's",
        "Which language I use to write code for Android",
        "Which of the following is the email addresses you can send an offer letter to?"
    )
    private val option = arrayOf(
        arrayOf(
            "User",
            "Kushal Sharma",
            "XYZ",
            "ABC"
        ),
        arrayOf("Rajasthan Institute of Engineering and Technology", "XYZ", "ABC", "None"),
        arrayOf(
            " 1 month",
            " 2 months",
            " 3 months",
            " 4 months"
        ),
        arrayOf("Word Frenzy", "News APP", "Weather App", "All of the Above"),
        arrayOf(
            " Semi-finalist team at Flipkart’s hackathon",
            "Under 500 in Amazon ML Hackathon",
            "Qualified 12 hrs Microsoft hackathon",
            "All of the Above"
        ),
        arrayOf("Weather App", "Word Frenzy", "Notes App", "NONE"),
        arrayOf("Weather App", "Word Frenzy", "Notes App", "NONE"),
        arrayOf("Firebase", "ROOM", "SQLite", "All of them"),
        arrayOf("Python", "Kotlin", "C++", "None"),
        arrayOf("ABC", "XYZ", "kushals0209@gmail.com", "None")
    )
    private val correctAnswer = arrayOf(1, 0, 2, 3, 3, 1, 1, 3, 1, 2)

    private var currentQuestionIndex = 0
    private var score = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestion()

        binding.option1Btn.setOnClickListener {
            checkAnswer(0)
        }
        binding.option2Btn.setOnClickListener {
            checkAnswer(1)
        }
        binding.option3Btn.setOnClickListener {
            checkAnswer(2)
        }
        binding.option4Btn.setOnClickListener {
            checkAnswer(3)
        }
        binding.resetBtn.setOnClickListener{
            restartQuiz()
        }


    }

    private fun correctButtonColor(buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.option1Btn.setBackgroundColor(Color.GREEN)
            1 -> binding.option2Btn.setBackgroundColor(Color.GREEN)
            2 -> binding.option3Btn.setBackgroundColor(Color.GREEN)
            3 -> binding.option4Btn.setBackgroundColor(Color.GREEN)

        }
    }

    private fun wrongButtonColor(buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.option1Btn.setBackgroundColor(Color.RED)
            1 -> binding.option2Btn.setBackgroundColor(Color.RED)
            2 -> binding.option3Btn.setBackgroundColor(Color.RED)
            3 -> binding.option4Btn.setBackgroundColor(Color.RED)
        }
    }

    private fun resetButtonColor() {
        binding.option1Btn.setBackgroundColor(Color.rgb(57, 62, 65))
        binding.option2Btn.setBackgroundColor(Color.rgb(57, 62, 65))
        binding.option3Btn.setBackgroundColor(Color.rgb(57, 62, 65))
        binding.option4Btn.setBackgroundColor(Color.rgb(57, 62, 65))
    }

    private fun showResult() {
        Toast.makeText(this, "Your score : $score out of ${question.size}", Toast.LENGTH_LONG)
            .show()
        binding.resetBtn.isEnabled = true
    }

    private fun displayQuestion() {
        binding.questionTV.text = question[currentQuestionIndex]
        binding.option1Btn.text = option[currentQuestionIndex][0]
        binding.option2Btn.text = option[currentQuestionIndex][1]
        binding.option3Btn.text = option[currentQuestionIndex][2]
        binding.option4Btn.text = option[currentQuestionIndex][3]
        resetButtonColor()
    }

    private fun checkAnswer(selectedAnswerIndex: Int) {
        val correctAnswerIndex = correctAnswer[currentQuestionIndex]
        binding.progressBar.progress = currentQuestionIndex+1

        if (selectedAnswerIndex == correctAnswerIndex) {
            score++
            correctButtonColor(selectedAnswerIndex)
        } else {
            wrongButtonColor(selectedAnswerIndex)
            correctButtonColor(correctAnswerIndex)
        }
        if (currentQuestionIndex < question.size - 1) {
            currentQuestionIndex++

            val pg = currentQuestionIndex+1
            binding.progressText.setText("$pg/10")
            binding.questionTV.postDelayed({ displayQuestion() }, 1000)
        } else {
            showResult()
        }

    }

    private fun restartQuiz() {
        currentQuestionIndex = 0
        score = 0
        displayQuestion()
        binding.resetBtn.isEnabled = false
    }


}