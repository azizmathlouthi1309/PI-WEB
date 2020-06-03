<?php

namespace EventBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Event
 *
 * @ORM\Table(name="event")
 * @ORM\Entity
 */
class Event
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="name", type="string", length=255, nullable=false)
     * @Assert\Length(min="6", minMessage="Must be greater than 6 characters")
     * @Assert\NotBlank(message="this champ is obligate")
     */
    private $name;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=255, nullable=false)
     * @Assert\Length(min="6", minMessage="Must be greater than 6 characters")
     * @Assert\NotBlank(message="this champ is obligate")
     */
    private $description;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="datetime", nullable=false)
     * @Assert\GreaterThanOrEqual("today")
     */
    private $date;

    /**
     * @var integer
     *
     * @ORM\Column(name="hour_begin", type="integer", nullable=false)
     * @Assert\Range(
     *      min = 8,
     *      max = 17,
     *      minMessage = "min 8",
     *      maxMessage = "max 17",
     *      invalidMessage = "error"
     * )
     */
    private $hourBegin;

    /**
     * @var integer
     *
     * @ORM\Column(name="hour_end", type="integer", nullable=false)
     * @Assert\Range(
     *      min = 8,
     *      max = 17,
     *      minMessage = "min 8",
     *      maxMessage = "max 17",
     *      invalidMessage = "error"
     * )
     *  @Assert\GreaterThanOrEqual(propertyPath="hourBegin",
     *     message = "La longueur doit être supérieure à l'hour begin"
     * )
     */
    private $hourEnd;

    /**
     * @var integer
     *
     * @ORM\Column(name="capacity", type="integer", nullable=false)
     */
    private $capacity;

    /**
     * @var string
     * @Assert\NotBlank(message="insert picture plzz")
     * @Assert\Image()
     * @ORM\Column(name="picture", type="string", length=255)
     */
    private $picture;
    /**
     * @return mixed
     */
    public function getForums()
    {
        return $this->forums;
    }
    /**
     * @var integer
     *
     * @ORM\Column(name="nbre", type="integer", nullable=true)
     */
    private $nbre;
    /**
     * @var integer
     *
     * @ORM\Column(name="nbreN", type="integer", nullable=true)
     */
    private $nbreN;
    /**
     * @param mixed $forums
     */
    public function setForums($forums)
    {
        $this->forums = $forums;
    }
    /**
     * @ORM\OneToMany(targetEntity="EventBundle\Entity\Event", mappedBy="event",cascade={"remove"}, orphanRemoval=true)
     */
    /**
     * @var \Forum
     *
     * @ORM\GeneratedValue(strategy="NONE")
     * @ORM\OneToOne(targetEntity="Forum", mappedBy="event",cascade={"remove"}, orphanRemoval=true)

     */
    private $forums;


    /**
     * @Assert\File(maxSize="5000k")
     */
    private $file;

    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set name
     *
     * @param string $name
     *
     * @return event
     */
    public function setName($name)
    {
        $this->name = $name;

        return $this;
    }

    /**
     * Get name
     *
     * @return string
     */
    public function getName()
    {
        return $this->name;
    }

    /**
     * Set description
     *
     * @param string $description
     *
     * @return Event
     */
    public function setDescription($description)
    {
        $this->description = $description;

        return $this;
    }

    /**
     * Get description
     *
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * Set date
     *
     * @param \DateTime $date
     *
     * @return Event
     */
    public function setDate($date)
    {
        $this->date = $date;

        return $this;
    }

    /**
     * Get date
     *
     * @return \DateTime
     */
    public function getDate()
    {
        return $this->date;
    }

    /**
     * Set hourBegin
     *
     * @param integer $hourBegin
     *
     * @return Event
     */
    public function setHourBegin($hourBegin)
    {
        $this->hourBegin = $hourBegin;

        return $this;
    }

    /**
     * Get hourBegin
     *
     * @return int
     */
    public function getHourBegin()
    {
        return $this->hourBegin;
    }

    /**
     * Set hourEnd
     *
     * @param integer $hourEnd
     *
     * @return Event
     */
    public function setHourEnd($hourEnd)
    {
        $this->hourEnd = $hourEnd;

        return $this;
    }

    /**
     * Get hourEnd
     *
     * @return int
     */
    public function getHourEnd()
    {
        return $this->hourEnd;
    }

    /**
     * Set capacity
     *
     * @param integer $capacity
     *
     * @return event
     */
    public function setCapacity($capacity)
    {
        $this->capacity = $capacity;

        return $this;
    }

    /**
     * Get capacity
     *
     * @return int
     */
    public function getCapacity()
    {
        return $this->capacity;
    }

    /**
     * Set picture
     *
     * @param string $picture
     *
     * @return Event
     */
    public function setPicture($picture)
    {
        $this->picture = $picture;

        return $this;
    }

    /**
     * Get picture
     *
     * @return string
     */
    public function getPicture()
    {
        return $this->picture;
    }


    /**
     * @return mixed
     */
    public function getFile()
    {
        return $this->file;
    }

    /**
     * @param mixed $file
     */
    public function setFile($file)
    {
        $this->file = $file;
    }
    protected function getUploadDir()
    {
        return 'uploads/images';
    }
    public function getAbsolutRoot()
    {
return $this->getUploadRoot().$this->picture;
    }

    public function getWebPath()
    {
        return  $this->getUploadDir().'/'.$this->picture;
    }
    protected  function getUploadRoot()
    {
        return __DIR__.'/../../../web/'.$this->getUploadDir().'/';
    }
    public function upload()
    {
     if($this->file === null)
     {
    return;
     }

      $this->picture = $this->file->getClientOriginalName();
//var_dump($this->getUploadRoot());
      if(!is_dir($this->getUploadRoot()))
{
    mkdir($this->getUploadRoot(),'0777',true);
}

        $this->file->move($this->getUploadDir(),$this->name);
        unset($this->file);
        #$this->picture=$this->getFile()->getClientOriginalName();
        #$this->file = null;

    }
    /**
     * @return int
     */
    public function getNbre()
    {
        return $this->nbre;
    }

    /**
     * @param int $nbre
     */
    public function setNbre($nbre)
    {
        $this->nbre = $nbre;
    }

    /**
     * @return mixed
     */
    public function getNbreN()
    {
        return $this->nbreN;
    }

    /**
     * @param mixed $nbreN
     */
    public function setNbreN($nbreN)
    {
        $this->nbreN = $nbreN;
    }
}

