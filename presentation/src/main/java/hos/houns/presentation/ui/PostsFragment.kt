package hos.houns.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import hos.houns.presentation.GetPostIntent
import hos.houns.presentation.GetPostState
import hos.houns.presentation.GetPostViewModel
import hos.houns.presentation.R
import hos.houns.presentation.base.BaseView
import hos.houns.presentation.databinding.PostFragmentBinding
import hos.houns.presentation.ui.delegate.viewBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import org.koin.android.viewmodel.ext.android.getSharedViewModel


@ExperimentalCoroutinesApi
@FlowPreview
class PostsFragment : Fragment(R.layout.post_fragment), BaseView<GetPostIntent, GetPostState> {

    private val binding by viewBinding(PostFragmentBinding::bind)
    private lateinit var viewModel: GetPostViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
    }

    private fun bindViewModel() {
        viewModel = getSharedViewModel()
       // viewModel.states().observe(viewLifecycleOwner, ::render)
        intents().onEach(viewModel::processIntents).launchIn(lifecycleScope)
    }

    override fun intents(): Flow<GetPostIntent> {
        return merge(flowOf(GetPostIntent.LoadPostIntent))
    }

    override fun render(state: GetPostState) {
        Log.e("PostsFragment", state.toString())
        when (state) {
            GetPostState.LoadingState -> {

            }
            is GetPostState.ErrorState -> {

            }
            is GetPostState.SuccessState -> {

                binding.rvTask.withModels {

                }

            }
        }
    }

    /*
    @Composable
    private fun PostCard(postUiModel: PostUiModel) {
        Card(
            shape = RoundedCornerShape(14.dp),
            backgroundColor = Color.White,
            modifier = Modifier.padding(10.dp).width(180.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
            ) {
                /*Image(
                    modifier = Modifier.size(140.dp),
                    asset = imageResource(id = postUiModel.image)
                )*/
                Row(modifier = Modifier.padding(top = 20.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = postUiModel.title,
                            style = TextStyle(
                                color = Color.DarkGray,
                                fontSize = 16.sp
                            )
                        )
                        Text(
                            text = postUiModel.peopleCount.toString(),
                            style = TextStyle(
                                color = Color.DarkGray,
                                fontSize = 16.sp
                            )
                        )
                    }
                    IconButton(
                        onClick = { },
                        modifier = Modifier.background(
                            color = Color.DarkGray,
                            shape = RoundedCornerShape(10.dp)
                        )
                    ) {
                        Icon(Icons.Default.Add, tint = Color.White)
                    }
                }
            }
        }
    }
*/
}

