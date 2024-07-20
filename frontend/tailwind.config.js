/** @type {import('tailwindcss').Config} */
module.exports = {
    content: ["./src/**/*.{html,ts,js}"],
    theme: {
        extend: {
            fontFamily: {
                sans: ["Inter", "system-ui", "sans-serif"],
            },
        },
    },
    plugins: [
        require('@tailwindcss/typography'),
        require("daisyui")
    ],
    daisyui: {
        themes: [
            {
                light: {
                    ...require("daisyui/src/theming/themes")["light"],
                    "primary": "#004dac",
                    "secondary": "#83941f",
                    "base-100": "#f9fafb",
                    "warning": "#e7a33e",
                },
            },
        ],
        // false: only light + dark | true: all themes | array: specific themes like this ["light", "dark", "cupcake"]
        darkTheme: "dark",
        // name of one of the included themes for dark mode
        base: true,
        // applies background color and foreground color for root element by default
        styled: true,
        // include daisyUI colors and design decisions for all components
        utils: true,
        // adds responsive and modifier utility classes
        prefix: "",
        // prefix for daisyUI classnames (components, modifiers and responsive class names. Not colors)
        logs: false,
        // Shows info about-profile daisyUI version and used config in the console when building your CSS
        themeRoot: ":root" // The element that receives theme color CSS variables
    }
}
