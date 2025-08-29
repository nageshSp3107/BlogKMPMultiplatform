package com.example.blogmultiplatform.styles

import com.example.blogmultiplatform.models.Theme
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.silk.style.CssStyle
import org.jetbrains.compose.web.css.AnimationTimingFunction
import org.jetbrains.compose.web.css.ms

val NavigationTextStyle = CssStyle {
    // Add CSS that targets children when parent is hovered
    cssRule(" > #navigationText") {
        Modifier
            .color(Theme.White.rgb)
            .transition(
                Transition.of("color", 300.ms, AnimationTimingFunction.EaseInOut),
                Transition.of("font-size", 200.ms, AnimationTimingFunction.Ease)
            )
    }
    cssRule(":hover > #navigationText") {
        Modifier.color(Theme.Primary.rgb)
    }

}