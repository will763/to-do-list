module.exports = {
  darkMode: 'class',
  content: ["./src/**/*.tsx"],
  theme: {
    extend: {
      fontFamily: {
        'josefin-sans': [ "'Josefin Sans'", 'sans-serif']
      },
       backgroundImage: {
        'mobile-dark': "url('/images/bg-mobile-dark.jpg')",
        'mobile-light': "url('/images/bg-mobile-light.jpg')",
        'desktop-dark': "url('/images/bg-desktop-dark.jpg')",
        'desktop-light': "url('/images/bg-desktop-light.jpg')"
      }
    },
  },
  plugins: [],
}
