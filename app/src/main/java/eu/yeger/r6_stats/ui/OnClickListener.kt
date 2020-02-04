package eu.yeger.r6_stats.ui

class OnClickListener<T>(private val block: (T) -> Unit) {
    fun onClick(input: T) = block(input)
}
