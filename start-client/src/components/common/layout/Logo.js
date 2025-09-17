import PropTypes from 'prop-types'
import React from 'react'

function Logo({ className }) {
  return (
      <svg
          aria-hidden="true"
          focusable="false"
          data-icon="nove-logo"
          role="img"
          xmlns="http://www.w3.org/2000/svg"
          x="0px"
          y="0px"
          className={className}
          viewBox="0 0 992.4 141.6"
          style={{ width: '600px', height: 'auto' }}
      >
        <g>
          <image
              href="/images/icon-256x256.png"
              x="0"
              y="0"
              style={{ width: '140px', height: 'auto' }}
              preserveAspectRatio="xMidYMid meet"
          />
        </g>
        <g>
          <text
              x="174"
              y="110"
              fontFamily="Metropolis, Segoe UI, Roboto, Arial, sans-serif"
              fontSize="90"
              fontWeight="700"
              fill="#ff7428"
              dominantBaseline="alphabetic"
          >
            Nova
          </text>

          <text
              x="415"
              y="110"
              fontFamily="Metropolis, Segoe UI, Roboto, Arial, sans-serif"
              fontSize="90"
              fontWeight="600"
              fill="#ffffff"
              dominantBaseline="alphabetic"
          >
            Initializr
          </text>
        </g>
      </svg>
  )
}

Logo.defaultProps = {
  className: '',
}

Logo.propTypes = {
  className: PropTypes.string,
}

export default Logo
