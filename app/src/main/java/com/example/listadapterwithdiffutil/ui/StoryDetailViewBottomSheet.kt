package com.example.listadapterwithdiffutil.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.listadapterwithdiffutil.MainActivity
import com.example.listadapterwithdiffutil.databinding.FragmentDetailViewLayoutBinding
import com.example.storyview.story.StoryDetailViewModel
import com.example.storyview.story.adapter.StoryDetailAdapter
import com.example.storyview.story.adapter.StoryHolderClickListener
import com.irmak.ui.model.StoryDetailDataUiModel

class StoryDetailViewFragment : Fragment() {
    private lateinit var binding: FragmentDetailViewLayoutBinding
    private lateinit var campaignDetailAdapter: StoryDetailAdapter
    private val progressBars = mutableListOf<ProgressBar>()
    private var currentStoryIndex = 0
    private var remainingTime: Long = PROGRESS_BAR_FULL
    private var isPaused: Boolean = false
    private lateinit var viewModel: StoryDetailViewModel
    private val handler = Handler(Looper.getMainLooper())

    private val storyAdapterListener = object : StoryHolderClickListener {
        override fun click(model: StoryDetailDataUiModel) {
            Toast.makeText(requireContext(),"tıklandı",Toast.LENGTH_SHORT).show()
        }

        override fun nextStory() {
            showNextStory()
        }

        override fun previousStory() {
            showPreviousStory()
        }

        override fun updateProgressState(isPause: Boolean) {
            if (isPause.not()) {
                handler.post(progressRunnable)
            } else {
                handler.removeCallbacks(progressRunnable)
            }
        }

    }

    private fun navigateToMainActivity() {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
    }

    private val progressRunnable = object : Runnable {
        override fun run() {
            if (remainingTime <= 0) {
                if (currentStoryIndex < viewModel.storyData.value?.size?.minus(1) ?: 0) {
                    currentStoryIndex++
                    binding.storyDetailRecyclerView.setCurrentItem(currentStoryIndex, true)
                    remainingTime = PROGRESS_BAR_FULL
                } else {
                    navigateToMainActivity()
                    return
                }
            }

            if (!isPaused) {
                remainingTime -= 100
                val progress = ((PROGRESS_BAR_FULL - remainingTime) / FIND_PERCENTAGA).toInt()
                progressBars[currentStoryIndex].progress = progress
                handler.postDelayed(this, HANDLER_DELAY_MILLIS)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailViewLayoutBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(StoryDetailViewModel::class.java)
        initBinding()
        dataObserve()
        closeBottomSheetFragment()

    }


    private fun initBinding() {
        with(binding) {
            binding.storyDetailRecyclerView.apply {
                isUserInputEnabled = false
                adapter = StoryDetailAdapter(storyAdapterListener).apply {
                    campaignDetailAdapter = this
                }
            }
        }

    }

    private fun dataObserve() {
        viewModel.storyData.observe(viewLifecycleOwner) { data ->
            campaignDetailAdapter.submitList(data)
            initializeProgressBars(data.size)
            handler.post(progressRunnable)
        }
    }

    private fun showNextStory() {
        if (currentStoryIndex < (viewModel.storyData.value?.size?.minus(1) ?: 0)) {
            currentStoryIndex++
            binding.storyDetailRecyclerView.setCurrentItem(currentStoryIndex, true)
            remainingTime = PROGRESS_BAR_FULL
            updateProgressBar()
        } else {
            navigateToMainActivity()
        }
    }

    private fun showPreviousStory() {
        if (currentStoryIndex > 0) {
            currentStoryIndex--
            binding.storyDetailRecyclerView.setCurrentItem(currentStoryIndex, true)
            remainingTime = PROGRESS_BAR_FULL
            updateProgressBar()
        }
    }

    private fun updateProgressBar() {
        for (i in 0 until progressBars.size) {
            val progress =
                if (i == currentStoryIndex) {
                    ((PROGRESS_BAR_FULL - remainingTime) / FIND_PERCENTAGA).toInt()
                } else if (i < currentStoryIndex) {
                    100
                } else {
                    0
                }
            progressBars[i].progress = progress
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(progressRunnable)
    }

    private fun initializeProgressBars(size: Int) {
        binding.progressBarsContainer.removeAllViews()
        progressBars.clear()
        for (i in 0 until size) {
            val progressBar = ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal)
            progressBar.layoutParams =
                LinearLayout.LayoutParams(
                    SCREEN_PARAMS_WIDHT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    SCREEN_PARAMS_WEIGHT
                )
            progressBar.setPadding(
                PROGRESS_PADDING_LEFT,
                PROGRESS_PADDING_TOP,
                PROGRESS_PADDING_RIGHT,
                PROGRESS_PADDING_BOTTOM
            )
            progressBar.max = 100
            progressBars.add(progressBar)
            binding.progressBarsContainer.addView(progressBar)
        }
    }

    private fun closeBottomSheetFragment() {
        binding.storyDetailCloseButton.setOnClickListener {
            navigateToMainActivity()
        }
    }

    companion object {
        const val PROGRESS_PADDING_LEFT = 0
        const val PROGRESS_PADDING_RIGHT = 25
        const val PROGRESS_PADDING_TOP = 0
        const val PROGRESS_PADDING_BOTTOM = 0
        const val SCREEN_PARAMS_WEIGHT = 1f
        const val SCREEN_PARAMS_WIDHT = 0
        const val PROGRESS_BAR_FULL = 10000L
        const val FIND_PERCENTAGA = 100L
        const val HANDLER_DELAY_MILLIS = 20L
    }

}
