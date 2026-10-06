package com.example.aiagentworkflow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aiagentworkflow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRunAgent.setOnClickListener {

            val userInput = binding.etInput.text.toString().trim()

            if (userInput.isEmpty()) {

                binding.tvResult.text = "Please enter something."

                binding.tvPerception.text =
                    "Perception: Waiting for input..."

                binding.tvReasoning.text =
                    "Reasoning: Waiting for input..."

                binding.tvAction.text =
                    "Action: Waiting for input..."

            } else {

                runAgent(userInput)
            }
        }

        binding.btnClear.setOnClickListener {

            binding.etInput.text.clear()

            binding.tvPerception.text =
                "Perception: Waiting..."

            binding.tvReasoning.text =
                "Reasoning: Waiting..."

            binding.tvAction.text =
                "Action: Waiting..."

            binding.tvResult.text =
                "Agent output will appear here."
        }
    }

    private fun runAgent(input: String) {

        // -----------------------------
        // STEP 1: PERCEPTION
        // -----------------------------

        val perception: String

        val lowerInput = input.lowercase()

        perception = when {

            lowerInput.contains("hot") ||
                    lowerInput.contains("temperature") ||
                    lowerInput.contains("heat") -> {

                "Detected: Hot environment"
            }

            lowerInput.contains("cold") ||
                    lowerInput.contains("cool") -> {

                "Detected: Cold environment"
            }

            lowerInput.contains("light") ||
                    lowerInput.contains("dark") -> {

                "Detected: Lighting condition"
            }

            lowerInput.contains("hello") ||
                    lowerInput.contains("hi") -> {

                "Detected: Greeting"
            }

            lowerInput.contains("water") ||
                    lowerInput.contains("thirsty") -> {

                "Detected: Water requirement"
            }

            else -> {

                "Detected: General input"
            }
        }

        binding.tvPerception.text =
            "Perception:\n$perception"


        // -----------------------------
        // STEP 2: REASONING
        // -----------------------------

        val reasoning: String

        val action: String

        when {

            lowerInput.contains("hot") ||
                    lowerInput.contains("temperature") ||
                    lowerInput.contains("heat") -> {

                reasoning =
                    "The environment is hot, so cooling is required."

                action =
                    "Turn ON the fan."
            }

            lowerInput.contains("cold") ||
                    lowerInput.contains("cool") -> {

                reasoning =
                    "The environment is cold, so heating is required."

                action =
                    "Turn OFF the fan."
            }

            lowerInput.contains("dark") -> {

                reasoning =
                    "The environment is dark, so additional lighting is required."

                action =
                    "Turn ON the light."
            }

            lowerInput.contains("light") -> {

                reasoning =
                    "The lighting condition is detected."

                action =
                    "Check or adjust the light."
            }

            lowerInput.contains("hello") ||
                    lowerInput.contains("hi") -> {

                reasoning =
                    "The user is greeting the assistant."

                action =
                    "Respond with a greeting."
            }

            lowerInput.contains("water") ||
                    lowerInput.contains("thirsty") -> {

                reasoning =
                    "The user needs water."

                action =
                    "Recommend drinking water."
            }

            else -> {

                reasoning =
                    "No specific rule matched the input."

                action =
                    "Provide a general response."
            }
        }

        binding.tvReasoning.text =
            "Reasoning:\n$reasoning"


        // -----------------------------
        // STEP 3: ACTION
        // -----------------------------

        binding.tvAction.text =
            "Action:\n$action"


        // -----------------------------
        // FINAL OUTPUT
        // -----------------------------

        val finalOutput = when {

            lowerInput.contains("hot") ||
                    lowerInput.contains("temperature") ||
                    lowerInput.contains("heat") -> {

                "🤖 Agent: It is hot. Fan turned ON."
            }

            lowerInput.contains("cold") ||
                    lowerInput.contains("cool") -> {

                "🤖 Agent: It is cold. Fan turned OFF."
            }

            lowerInput.contains("dark") -> {

                "🤖 Agent: It is dark. Light turned ON."
            }

            lowerInput.contains("hello") ||
                    lowerInput.contains("hi") -> {

                "🤖 Agent: Hello! How can I help you?"
            }

            lowerInput.contains("water") ||
                    lowerInput.contains("thirsty") -> {

                "🤖 Agent: Please drink some water."
            }

            else -> {

                "🤖 Agent: I understood your input, but no specific action was found."
            }
        }

        binding.tvResult.text = finalOutput
    }
}